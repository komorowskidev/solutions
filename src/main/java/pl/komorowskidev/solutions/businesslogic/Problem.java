package pl.komorowskidev.solutions.businesslogic;

public interface Problem {

    String getName();

    String getDescription();

    String getSolution(String data);

    String getExampleData();

}
