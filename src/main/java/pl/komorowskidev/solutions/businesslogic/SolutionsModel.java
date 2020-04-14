package pl.komorowskidev.solutions.businesslogic;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class SolutionsModel {

    private Map<String, Problem> problemMap;

    public SolutionsModel() {
        problemMap = new HashMap<>();
    }

    public void addProblem(Problem problem){
        problemMap.put(problem.getName(), problem);
    }

    public Set<String> getProblemNameSet() {
        return problemMap.keySet();
    }

    public Optional<Problem> getProblem(String problemName){
        return Optional.ofNullable(problemMap.get(problemName));
    }

}
