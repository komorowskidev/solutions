package pl.komorowskidev.solutions.gui.main;

import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;
import pl.komorowskidev.solutions.businesslogic.Model;
import pl.komorowskidev.solutions.businesslogic.Problem;
import pl.komorowskidev.solutions.gui.BasePresenter;

@Component
public class MainPresenter extends BasePresenter<MainContract.ViewController>
        implements MainContract.Presenter {

    private String applicationVersion;

    private Model model;

    private Problem problem;

    public MainPresenter(BuildProperties buildProperties, Model model) {
        this.applicationVersion = buildProperties.getVersion();
        this.model = model;
    }

    @Override
    public void attachViewController(MainViewController mainViewController) {
        attach(mainViewController);
    }

    @Override
    public void viewPrepared() {
        view.setApplicationVersion(applicationVersion);
        view.setProblemsNames(model.getProblemNameSet());
    }

    @Override
    public void problemChanged(String problemName) {
        view.setStartButtonDisable(true);
        model.getProblem(problemName).ifPresent((newProblem) -> {
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
        view.showResult(data);
    }
}
