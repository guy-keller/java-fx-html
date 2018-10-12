package my.sample.app.guice.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiceAppMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(GuiceAppMain.class.getResource("/GuiceAppUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Sample JavaFX with Guice HTML CSS JS");
        stage.setScene(scene);
        stage.show();
    }

}
