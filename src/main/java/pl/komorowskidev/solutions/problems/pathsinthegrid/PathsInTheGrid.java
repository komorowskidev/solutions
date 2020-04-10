package pl.komorowskidev.solutions.problems.pathsinthegrid;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutions.businesslogic.Model;
import pl.komorowskidev.solutions.businesslogic.Problem;
import pl.komorowskidev.solutions.exception.DataNotValidException;
import pl.komorowskidev.solutions.util.LinesFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PathsInTheGrid implements Problem {

    private LinesFactory linesFactory;

    private PathsInTheGridValidator validator;

    public PathsInTheGrid(Model model, LinesFactory linesFactory, PathsInTheGridValidator validator) {
        model.addProblem(this);
        this.linesFactory = linesFactory;
        this.validator = validator;
    }

    @Override
    public String getName() {
        return "Paths in the Grid";
    }

    @Override
    public String getDescription() {
        return "Travel from upper left corner (marked with @) to lower right corner (marked with $). You only can move by safe squares, and from each square you only can move in two directions - either right or down. \nX is a pit, + is safe path. \nHow many different paths there exist from one corner to another under given rules?";
    }

    @Override
    public String getSolution(String data) throws DataNotValidException {
        List<String> lines = linesFactory.createLines(data);
        lines = linesFactory.removeSpaces(lines);
        validator.validate(lines);

        return linesFactory.createString(lines);
    }

    @Override
    public String getExampleData() {
        return "@ + + + +\n" +
                "+ + + X X\n" +
                "+ X + + +\n" +
                "+ + + X +\n" +
                "+ X + + X\n" +
                "+ + + + $";
    }
}
