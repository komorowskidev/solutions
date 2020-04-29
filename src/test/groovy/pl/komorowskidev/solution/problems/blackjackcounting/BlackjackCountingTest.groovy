package pl.komorowskidev.solution.problems.blackjackcounting

import pl.komorowskidev.solutions.businesslogic.SolutionsModel
import pl.komorowskidev.solutions.problems.blackjackcounting.BlackjackCounter
import pl.komorowskidev.solutions.problems.blackjackcounting.BlackjackCounting
import pl.komorowskidev.solutions.util.LinesProcessor
import spock.lang.Specification

class BlackjackCountingTest extends Specification {

    BlackjackCounting blackjackCounting

    SolutionsModel modelMock

    LinesProcessor linesProcessorMock

    BlackjackCounter blackjackCounterMock

    def setup(){
        modelMock = Mock(SolutionsModel)
        linesProcessorMock = Mock(LinesProcessor)
        blackjackCounterMock = Mock(BlackjackCounter)
        blackjackCounting = new BlackjackCounting(modelMock, linesProcessorMock, blackjackCounterMock)
    }

    def "should call LinesProcessor"(){
        given:
        def data = "test1\ntest2"
        def lines = ["test1", "test2"]

        when:
        blackjackCounting.getSolution(data)

        then:
        1 * linesProcessorMock.createLines(data) >> lines
    }

    def "should call BlackjackCounter"(){
        given:
        def data = "test1\ntest2"
        def lines = ["2 3 4", "T Q K"]
        linesProcessorMock.createLines(data) >> lines

        when:
        blackjackCounting.getSolution(data)

        then:
        1 * blackjackCounterMock.count(["2", "3", "4"] as String[]) >> "result1"
        1 * blackjackCounterMock.count(["T", "Q", "K"] as String[]) >> "result2"
    }

    def "should return solution"(){
        given:
        def data = "test1\ntest2"
        def lines = ["2 3 4", "T Q K"]
        linesProcessorMock.createLines(data) >> lines
        blackjackCounterMock.count(["2", "3", "4"] as String[]) >> "result1"
        blackjackCounterMock.count(["T", "Q", "K"] as String[]) >> "result2"

        when:
        def actual = blackjackCounting.getSolution(data)

        then:
        actual == "result1 result2"
    }

}
