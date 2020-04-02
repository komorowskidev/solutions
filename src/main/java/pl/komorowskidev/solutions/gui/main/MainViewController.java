package pl.komorowskidev.solutions.gui.main;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import pl.komorowskidev.solutions.Messages;

import java.util.Set;

@Component
public class MainViewController implements MainContract.ViewController {

    @FXML
    private Label problemNameLabel;

    @FXML
    private ComboBox<String> problemNameComboBox;

    @FXML
    private Label descriptionLabel;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label dataLabel;

    @FXML
    private TextArea dataTextArea;

    @FXML
    private Button startButton;

    @FXML
    private Label resultLabel;

    @FXML
    private TextArea resultTextArea;

    private MainContract.Presenter presenter;

    private Messages messages;

    private Stage stage;

    public MainViewController(MainContract.Presenter presenter, Messages messages){
        this.presenter = presenter;
        this.messages = messages;
        presenter.attachViewController(this);
    }

    public void setDefaultView(Stage stage) {
        this.stage = stage;
        presenter.viewPrepared();
    }

    @FXML
    private void initialize() {
        setTexts();
        setListeners();
    }

    private void setTexts() {
        problemNameLabel.setText(messages.get("main.problemNameLabel"));
        descriptionLabel.setText(messages.get("main.descriptionLabel"));
        dataLabel.setText(messages.get("main.dataLabel"));
        startButton.setText(messages.get("main.startSolvingButton"));
        resultLabel.setText(messages.get("main.resultLabel"));
    }

    private void setListeners() {
        startButton.setOnAction(event -> {
            startButton.setDisable(true);
            presenter.startSolving(dataTextArea.getText());
        });

        problemNameComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                presenter.problemChanged(newValue);
            }
        });
    }

    @Override
    public void setApplicationVersion(String version) {
        stage.setTitle(messages.get("main.applicationName") + " v." + version);
    }

    @Override
    public void setProblemsNames(Set<String> problemsNameSet) {
        Platform.runLater(
                () -> {
                    problemNameComboBox.getItems().setAll(problemsNameSet);
                    problemNameComboBox.getSelectionModel().selectFirst();
                }
        );
    }

    @Override
    public void showResult(String result) {
        Platform.runLater(
                () -> {
                    resultTextArea.setText(result);
                    startButton.setDisable(false);
                }
        );
    }

    @Override
    public void setDescription(String description) {
        Platform.runLater(
                () -> descriptionTextArea.setText(description)
        );
    }

    @Override
    public void setExampleData(String example) {
        Platform.runLater(
                () -> {
                    dataTextArea.setText(example);
                    resultTextArea.clear();
                }
        );
    }

}
