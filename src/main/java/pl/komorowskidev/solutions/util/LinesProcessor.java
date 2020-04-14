package pl.komorowskidev.solutions.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinesProcessor {

    public List<String> createLines(String data){
        List<String> result = new ArrayList<>();
        if(data != null){
            String[] lines = data.trim().split("\n");
            for(String line : lines){
                if(!line.isEmpty()){
                    result.add(line);
                }
            }
        }
        return result;
    }

    public String createString(List<String> lines){
        return String.join("\n", lines);
    }

    public List<String> removeSpaces(List<String> lines) {
        List<String> result = new ArrayList<>();
        for(String line : lines){
            result.add(line.replaceAll(" ", ""));
        }
        return result;
    }

    public char[][] createCharArray(List<String> lines) {
        int rows = lines.size();
        int columns = lines.get(0).length();
        char[][] result = new char[rows][columns];
        for(int rowIndex = 0; rowIndex < rows; rowIndex++){
            result[rowIndex] = getRow(columns, lines.get(rowIndex));
        }
        return result;
    }

    private char[] getRow(int columns, String line) {
        char[] row = new char[columns];
        for(int columnIndex = 0; columnIndex < columns; columnIndex++){
            if(columnIndex < line.length()){
                row[columnIndex] = line.charAt(columnIndex);
            } else {
                row[columnIndex] = ' ';
            }
        }
        return row;
    }
}
