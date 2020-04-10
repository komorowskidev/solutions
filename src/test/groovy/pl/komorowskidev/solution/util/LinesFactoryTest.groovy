package pl.komorowskidev.solution.util

import pl.komorowskidev.solutions.util.LinesFactory
import spock.lang.Specification

class LinesFactoryTest extends Specification {

    def linesFactory = new LinesFactory()

    def "createLines should return empty list when null"(){
        given:
        String data = null
        List<String> expected = new ArrayList<>()

        when:
        List<String> actual = linesFactory.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return empty list when empty string"(){
        given:
        String data = ""
        List<String> expected = new ArrayList<>()

        when:
        List<String> actual = linesFactory.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return empty list when new line only"(){
        given:
        String data = "\n"
        List<String> expected = new ArrayList<>()

        when:
        List<String> actual = linesFactory.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return one line when one line"(){
        given:
        String data = "one line"
        List<String> expected = new ArrayList<>()
        expected.add("one line")

        when:
        List<String> actual = linesFactory.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return one line when another lines are empty"(){
        given:
        String data = "one line\n\n\n"
        List<String> expected = new ArrayList<>()
        expected.add("one line")

        when:
        List<String> actual = linesFactory.createLines(data)

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
        List<String> actual = linesFactory.createLines(data)

        then:
        expected == actual
    }

    def "createString should return empty String when lines is empty"(){
        given:
        List<String> lines = new ArrayList<>()

        when:
        String actual = linesFactory.createString(lines)

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
        String actual = linesFactory.createString(lines)

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
        String actual = linesFactory.createString(lines)

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
        List<String> actual = linesFactory.removeSpaces(lines)

        then:
        expected == actual
    }
}
