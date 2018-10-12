package my.sample.app.core.main;

import com.github.guikeller.javafxhtml.CoreJavaFxHtml;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import my.sample.app.core.ui.controller.SampleController;
import my.sample.app.core.ui.html.CoreView;

import java.net.URL;
import java.util.ResourceBundle;

public class CoreAppFxController implements Initializable {

    // These need to be instance variable, otherwise the GC will collect them
    private CoreJavaFxHtml fxHtml;
    private SampleController sampleController;

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Loads the HTML page
        WebEngine webEngine = this.webView.getEngine();
        URL url = CoreView.class.getResource("/core/index.html");
        webEngine.load(url.toExternalForm());
        // CoreJavaFxHtml Bridge
        this.fxHtml = new CoreJavaFxHtml(webEngine);
        this.sampleController = new SampleController();
        // This way does not pre-populate the view
//        this.fxHtml.registerFxHtml("javaFxHtml");
        // Pre-populates the view upon registering
        this.fxHtml.registerFxHtml("javaFxHtml", this.sampleController);
    }

}

