package my.sample.app.guice.ui.controller;

import com.github.guikeller.javafxhtml.annotations.FxControllerMethod;
import com.github.guikeller.javafxhtml.annotations.FxHtmlInitMethod;
import com.google.inject.Singleton;
import my.sample.app.guice.ui.model.GuiceSampleModel;

import java.time.Instant;

@Singleton
public class GuiceSampleController {

    public GuiceSampleController() {
        super();
    }

    public void foo() {
        System.out.println("FOO");
    }

    // This is optional
    // Useful if you want to pre-populate some fields for example
    @FxHtmlInitMethod
    public Object initHtmlFx() {
        // This could be retrieved through a 'Repository' for example
        GuiceSampleModel model = new GuiceSampleModel();
        model.setId(648);
        model.setValue("Automagic!");
        model.setToday(Instant.now());
        return model;
    }

    // * Disclaimer - Framework Limitations
    // 1) only the first param gets populated with the values from the view / core
    // 2) it does not support composition / aggregation - [ 1 model X 1 view ]
    @FxControllerMethod
    public String buttonPressed(GuiceSampleModel model) {
        System.out.println("ID: " + model.getId());
        System.out.println("VALUE: " + model.getValue());
        System.out.println("DATE: " + model.getToday());
        // Think of the possibilities with JSON / Jackson
        return "{\"result\": \"success\", \"timestamp\": \"" + Instant.now() + "\"}";
    }

    protected void bar() {
        System.out.println("BAR");
    }

}
