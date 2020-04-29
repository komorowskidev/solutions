package pl.komorowskidev.solution.problems.median

import pl.komorowskidev.solutions.problems.median.MedianEvenNumberOfElements
import pl.komorowskidev.solutions.problems.median.NumberListModifier
import pl.komorowskidev.solutions.problems.median.Separator
import spock.lang.Specification

class MedianEvenNumberOfElementsTest extends Specification {

    MedianEvenNumberOfElements medianEvenNumberOfElements

    def separator = new Separator()
    def numberListModifier = new NumberListModifier()

    def setup(){
        medianEvenNumberOfElements = new MedianEvenNumberOfElements(separator, numberListModifier)
    }

    def "should find result"(){
        expect:
        medianEvenNumberOfElements.findMedian(data) == result

        where:
        data                                    || result
        [7d, 3d]                                || 5d
        [11d, -5d, 111d, 0d]                    || 5.5d
        [3d, 1.3d, 1000.44d, -30.2d, -1d, 0d]   || 0.65d
    }

}
