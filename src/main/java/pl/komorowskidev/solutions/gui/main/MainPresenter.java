package pl.komorowskidev.solutions.gui.main;

import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;
import pl.komorowskidev.solutions.gui.BasePresenter;

import java.util.Set;
import java.util.TreeSet;

@Component
public class MainPresenter extends BasePresenter<MainContract.ViewController>
        implements MainContract.Presenter {

    private String applicationVersion;

    public MainPresenter(BuildProperties buildProperties) {
        this.applicationVersion = buildProperties.getVersion();
    }

    @Override
    public void attachViewController(MainViewController mainViewController) {
        attach(mainViewController);
    }

    @Override
    public void viewPrepared() {
        view.setApplicationVersion(applicationVersion);
        Set<String> problemsNameSet = new TreeSet<>();
        problemsNameSet.add("prob1");
        problemsNameSet.add("prob2");
        view.setProblemsNames(problemsNameSet);
    }

    @Override
    public void problemChanged(String problemName) {
        view.setDescription("description " + problemName);
        view.setExampleData("example " + problemName);
    }

    @Override
    public void startSolving(String data) {
        view.showResult(data);
    }
}
