package my.sample.app.core.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CoreAppMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(CoreAppMain.class.getResource("/CoreAppUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Sample JavaFX with HTML CSS JS");
        stage.setScene(scene);
        stage.show();
    }

}
