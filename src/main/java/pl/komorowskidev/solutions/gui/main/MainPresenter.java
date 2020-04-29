package pl.komorowskidev.solutions.gui.main;

import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;
import pl.komorowskidev.solutions.businesslogic.SolutionsModel;
import pl.komorowskidev.solutions.problems.Problem;
import pl.komorowskidev.solutions.exception.DataNotValidException;
import pl.komorowskidev.solutions.gui.BasePresenter;

@Component
public class MainPresenter extends BasePresenter<MainContract.ViewController>
        implements MainContract.Presenter {

    private String applicationVersion;

    private SolutionsModel solutionsModel;

    private Problem problem;

    public MainPresenter(BuildProperties buildProperties, SolutionsModel solutionsModel) {
        this.applicationVersion = buildProperties.getVersion();
        this.solutionsModel = solutionsModel;
    }

    @Override
    public void attachViewController(MainViewController mainViewController) {
        attach(mainViewController);
    }

    @Override
    public void viewPrepared() {
        view.setApplicationVersion(applicationVersion);
        view.setProblemsNames(solutionsModel.getProblemNameSet());
    }

    @Override
    public void problemChanged(String problemName) {
        view.setStartButtonDisable(true);
        solutionsModel.getProblem(problemName).ifPresent((newProblem) -> {
            problem = newProblem;
            refreshView();
        });
    }

    private void refreshView(){
        view.setStartButtonDisable(false);
        view.setDescription(problem.getDescription());
        view.setExampleData(problem.getExampleData());
    }

    @Override
    public void startSolving(String data) {
        view.setStartButtonDisable(true);
        view.showResult("working...");
        new Thread(() -> {
            try {
                view.showResult(problem.getSolution(data));
            } catch (DataNotValidException e) {
                view.showResult("Data is not valid. \n" + e.getMessage());
            }
            view.setStartButtonDisable(false);
        }).start();
    }
}
