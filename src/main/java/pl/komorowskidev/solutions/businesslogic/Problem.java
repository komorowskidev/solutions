package pl.komorowskidev.solutions.businesslogic;

import pl.komorowskidev.solutions.exception.DataNotValidException;

public interface Problem {

    String getName();

    String getDescription();

    String getSolution(String data) throws DataNotValidException;

    String getExampleData();

}
