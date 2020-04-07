package pl.komorowskidev.solutions.gui.main;

import java.util.Set;

public interface MainContract {

    interface ViewController {
        void setApplicationVersion(String title);
        void showResult(String result);
        void setProblemsNames(Set<String> problemsNameSet);
        void setDescription(String description);
        void setExampleData(String example);
        void setStartButtonDisable(boolean disabled);
    }

    interface Presenter {
        void attachViewController(MainViewController mainViewController);
        void viewPrepared();
        void problemChanged(String problemName);
        void startSolving(String data);
    }
}
