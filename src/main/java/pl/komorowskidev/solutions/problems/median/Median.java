package pl.komorowskidev.solutions.problems.median;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutions.problems.Problem;
import pl.komorowskidev.solutions.businesslogic.SolutionsModel;
import pl.komorowskidev.solutions.exception.DataNotValidException;
import pl.komorowskidev.solutions.util.LinesProcessor;

import java.util.*;

@Component
public class Median implements Problem {

    private MedianOddNumberOfElements medianOddNumberOfElements;

    private MedianEvenNumberOfElements medianEvenNumberOfElements;

    private LinesProcessor linesProcessor;

    public Median(SolutionsModel model, LinesProcessor linesProcessor,
                  MedianOddNumberOfElements medianOddNumberOfElements,
                  MedianEvenNumberOfElements medianEvenNumberOfElements) {
        this.medianOddNumberOfElements = medianOddNumberOfElements;
        this.medianEvenNumberOfElements = medianEvenNumberOfElements;
        this.linesProcessor = linesProcessor;
        model.addProblem(this);
    }

    @Override
    public String getName() {
        return "Median";
    }

    @Override
    public String getDescription() {
        return "Find the middle of numbers.";
    }

    @Override
    public String getExampleData() {
        return "7 3 5\n15 20 40\n300 550 137";
    }

    @Override
    public String getSolution(String data) throws DataNotValidException {
        List<String> lines = linesProcessor.createLines(data);
        String delimiter = " ";
        List<String> resultList = new ArrayList<>();
        for (String line : lines) {
            List<Double> numbers = linesProcessor.createListOfDouble(line, delimiter);
            resultList.add(String.format("%.1f", findMedian(numbers)));
        }
        return String.join(" ", resultList);
    }

    private Double findMedian(List<Double> numbers) throws DataNotValidException {
        if(numbers.isEmpty()) {
            throw new DataNotValidException("Empty list of numbers");
        }
        Double result = null;
        if(numbers.size() == 1){
            result = numbers.get(0);
        }else if (isSizeOdd(numbers)) {
            result = medianOddNumberOfElements.findMedian(numbers);
        } else {
            result = medianEvenNumberOfElements.findMedian(numbers);
        }
        return result;
    }

    private boolean isSizeOdd(List<Double> numbers) {
        return (numbers.size() % 2) != 0;
    }

}
