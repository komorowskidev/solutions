package pl.komorowskidev.solution.util

import pl.komorowskidev.solutions.util.LinesProcessor
import spock.lang.Specification

class LinesProcessorTest extends Specification {

    def linesProcessor = new LinesProcessor()

    def "createLines should return empty list when null"(){
        given:
        String data = null
        List<String> expected = new ArrayList<>()

        when:
        List<String> actual = linesProcessor.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return empty list when empty string"(){
        given:
        String data = ""
        List<String> expected = new ArrayList<>()

        when:
        List<String> actual = linesProcessor.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return empty list when new line only"(){
        given:
        String data = "\n"
        List<String> expected = new ArrayList<>()

        when:
        List<String> actual = linesProcessor.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return one line when one line"(){
        given:
        String data = "one line"
        List<String> expected = new ArrayList<>()
        expected.add("one line")

        when:
        List<String> actual = linesProcessor.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return one line when another lines are empty"(){
        given:
        String data = "one line\n\n\n"
        List<String> expected = new ArrayList<>()
        expected.add("one line")

        when:
        List<String> actual = linesProcessor.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return two lines when two lines"(){
        given:
        String data = "one line\n second line\n"
        List<String> expected = new ArrayList<>()
        expected.add("one line")
        expected.add(" second line")

        when:
        List<String> actual = linesProcessor.createLines(data)

        then:
        expected == actual
    }

    def "createString should return empty String when lines is empty"(){
        given:
        List<String> lines = new ArrayList<>()

        when:
        String actual = linesProcessor.createString(lines)

        then:
        actual == ""
    }

    def "createString should return one lines when one element"(){
        given:
        String first = "one line"
        List<String> lines = new ArrayList<>()
        lines.add(first)
        String expected = first

        when:
        String actual = linesProcessor.createString(lines)

        then:
        expected == actual
    }

    def "createString should return three lines when three element"(){
        given:
        String first = "one line"
        String second = "two lines"
        String third = "three line"
        List<String> lines = new ArrayList<>()
        lines.add(first)
        lines.add(second)
        lines.add(third)
        String expected = first + "\n" + second + "\n" + third

        when:
        String actual = linesProcessor.createString(lines)

        then:
        expected == actual
    }

    def "removingSpaces test"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("   one line    second third   ")
        lines.add("twolines")
        lines.add("three line")
        List<String> expected = new ArrayList<>()
        expected.add("onelinesecondthird")
        expected.add("twolines")
        expected.add("threeline")

        when:
        List<String> actual = linesProcessor.removeSpaces(lines)

        then:
        expected == actual
    }

    def "creating charArray should add space when lines shorter then first line"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("onel")
        lines.add("two")
        lines.add("four")
        char[][] expected = [['o', 'n', 'e', 'l'], ['t', 'w', 'o', ' '], ['f', 'o', 'u', 'r']]

        when:
        char[][] actual = linesProcessor.createCharArray(lines)

        then:
        actual == expected
    }

    def "creating charArray should cut line when longer then first line"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("onel")
        lines.add("threee")
        lines.add("four")
        char[][] expected = [['o', 'n', 'e', 'l'], ['t', 'h', 'r', 'e'], ['f', 'o', 'u', 'r']]

        when:
        char[][] actual = linesProcessor.createCharArray(lines)

        then:
        actual == expected
    }
}
