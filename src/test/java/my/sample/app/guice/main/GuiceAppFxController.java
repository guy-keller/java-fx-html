package my.sample.app.guice.main;

import com.github.guikeller.javafxhtml.CoreJavaFxHtml;
import com.github.guikeller.javafxhtml.GuiceJavaFxHtml;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import my.sample.app.guice.GuiceInjectorHolder;
import my.sample.app.guice.ui.controller.GuiceSampleController;
import my.sample.app.guice.ui.html.GuiceView;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiceAppFxController implements Initializable {

    // These need to be instance variable, otherwise the GC will collect them
    private Injector injector;
    private GuiceSampleController sampleController;
    private CoreJavaFxHtml fxHtml;

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Loads the HTML page
        WebEngine webEngine = this.webView.getEngine();
        URL url = GuiceView.class.getResource("/guice/index.html");
        webEngine.load(url.toExternalForm());

        // Guicefy
        this.injector = GuiceInjectorHolder.getInjector();
        this.sampleController = injector.getInstance(GuiceSampleController.class);

        // GuiceJavaFxHtml Bridge
        this.fxHtml = new GuiceJavaFxHtml(webEngine, this.injector);
        // Pre-populates the view upon registering
        this.fxHtml.registerFxHtml("javaFxHtml", this.sampleController);
        // This way does not pre-populate the view
//        this.fxHtml.registerFxHtml("javaFxHtml");
    }

}

