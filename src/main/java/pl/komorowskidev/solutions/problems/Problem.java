package pl.komorowskidev.solutions.problems;

import pl.komorowskidev.solutions.exception.DataNotValidException;

public interface Problem {

    String getName();

    String getDescription();

    String getExampleData();

    String getSolution(String data) throws DataNotValidException;

}
