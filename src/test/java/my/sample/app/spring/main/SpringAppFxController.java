package my.sample.app.spring.main;

import com.github.guikeller.javafxhtml.CoreJavaFxHtml;
import com.github.guikeller.javafxhtml.SpringJavaFxHtml;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import my.sample.app.spring.SpringContextHolder;
import my.sample.app.spring.ui.controller.SpringSampleController;
import my.sample.app.spring.ui.html.SpringView;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;

public class SpringAppFxController implements Initializable {

    private ApplicationContext applicationContext;
    private SpringSampleController sampleController;
    private CoreJavaFxHtml fxHtml;

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Loads the HTML page
        WebEngine webEngine = this.webView.getEngine();
        URL url = SpringView.class.getResource("/spring/index.html");
        webEngine.load(url.toExternalForm());

        // Springify
        this.applicationContext = SpringContextHolder.getApplicationContext();
        this.sampleController = applicationContext.getBean(SpringSampleController.class);

        // CoreJavaFxHtml Bridge
        this.fxHtml = new SpringJavaFxHtml(webEngine, this.applicationContext);
        // Pre-populates the view
        this.fxHtml.registerFxHtml("javaFxHtml", this.sampleController);
        // This is not pre-populated
//        this.fxHtml.registerFxHtml("javaFxHtml");
    }

}
