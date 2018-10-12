package my.sample.app.spring.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpringAppMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(SpringAppMain.class.getResource("/SpringAppUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Sample JavaFX with HTML CSS JS");
        stage.setScene(scene);
        stage.show();
    }

}
