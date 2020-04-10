package pl.komorowskidev.solutions.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinesFactory {

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
}
