package pl.komorowskidev.solutions;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.komorowskidev.solutions.gui.main.MainViewController;

@SpringBootApplication
public class ApplicationLauncher extends Application {

    private ConfigurableApplicationContext springContext;
    private FXMLLoader fxmlLoader;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(ApplicationLauncher.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            fxmlLoader.setLocation(getClass().getResource("/fxml/Main.fxml"));
            Parent rootNode = fxmlLoader.load();
            Scene scene = new Scene(rootNode);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(650);
            primaryStage.setMinHeight(500);
            primaryStage.show();
            MainViewController mainView = fxmlLoader.getController();
            mainView.setDefaultView(primaryStage);
        } catch(Exception e) {
            e.printStackTrace();
            stop();
        }
    }

    @Override
    public void stop() {
        springContext.close();
        Platform.exit();
        System.exit(0);
    }
}
