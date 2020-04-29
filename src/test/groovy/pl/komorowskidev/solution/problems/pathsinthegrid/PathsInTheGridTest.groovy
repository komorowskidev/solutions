package pl.komorowskidev.solution.problems.pathsinthegrid

import pl.komorowskidev.solutions.businesslogic.SolutionsModel
import pl.komorowskidev.solutions.problems.pathsinthegrid.PathsInTheGrid
import pl.komorowskidev.solutions.problems.pathsinthegrid.PathsInTheGridValidator
import pl.komorowskidev.solutions.util.LinesProcessor
import spock.lang.Specification

class PathsInTheGridTest extends Specification {

    SolutionsModel solutionsModelMock

    LinesProcessor linesProcessorMock

    PathsInTheGridValidator validatorMock

    PathsInTheGrid pathsInTheGrid

    def setup(){
        solutionsModelMock = Mock(SolutionsModel)
        linesProcessorMock = Mock(LinesProcessor)
        validatorMock = Mock(PathsInTheGridValidator)
        pathsInTheGrid = new PathsInTheGrid(solutionsModelMock, linesProcessorMock, validatorMock)
    }

    def "should call LinesProcessor.createLines"(){
        given:
        String data = "test1"
        List<String> lines = new ArrayList<>()
        lines.add("test2")
        List<String> linesWithoutSpaces = new ArrayList<>()
        linesWithoutSpaces.add("test3")
        linesProcessorMock.removeSpaces(lines) >> linesWithoutSpaces
        def elements = [['a'], ['b']] as char[][]
        linesProcessorMock.createCharArray(linesWithoutSpaces) >> elements

        when:
        pathsInTheGrid.getSolution(data)

        then:
        1 * linesProcessorMock.createLines(data) >> lines
    }

    def "should call LinesProcessor.removeSpaces"(){
        given:
        String data = "test1"
        List<String> lines = new ArrayList<>()
        lines.add("test2")
        def linesWithoutSpaces = new ArrayList<String>()
        linesWithoutSpaces.add("test3")
        linesProcessorMock.createLines(data) >> lines
        def elements = [['a'], ['b']] as char[][]
        linesProcessorMock.createCharArray(_ as List<String>) >> elements

        when:
        pathsInTheGrid.getSolution(data)

        then:
        1 * linesProcessorMock.removeSpaces(lines) >> linesWithoutSpaces
    }

    def "should call LinesProcessor.createCharArray"(){
        given:
        String data = "test1"
        List<String> lines = new ArrayList<>()
        lines.add("test2")
        List<String> linesWithoutSpaces = new ArrayList<>()
        linesWithoutSpaces.add("test3")
        linesProcessorMock.createLines(data) >> lines
        linesProcessorMock.removeSpaces(lines) >> linesWithoutSpaces
        def elements = [['a'], ['b']] as char[][]

        when:
        pathsInTheGrid.getSolution(data)

        then:
        1 * linesProcessorMock.createCharArray(linesWithoutSpaces) >> elements
    }

    def "should find result"(){
        given:
        String data = "test1"
        List<String> lines = new ArrayList<>()
        lines.add("test2")
        List<String> linesWithoutSpaces = new ArrayList<>()
        linesWithoutSpaces.add("test3")
        linesProcessorMock.createLines(data) >> lines
        linesProcessorMock.removeSpaces(lines) >> linesWithoutSpaces
        def elements = [
                ['@', '+', '+', '+', '+'],
                ['+', '+', '+', 'X', 'X'],
                ['+', 'X', '+', '+', '+'],
                ['+', '+', '+', 'X', '+'],
                ['+', 'X', '+', '+', 'X'],
                ['+', '+', '+', '+', '$']] as char[][]
        linesProcessorMock.createCharArray(linesWithoutSpaces) >> elements
        def expected = "Number of paths: 9"

        when:
        def actual = pathsInTheGrid.getSolution(data)

        then:
        actual == expected
    }

}
