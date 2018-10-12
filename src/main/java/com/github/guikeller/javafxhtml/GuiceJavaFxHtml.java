package com.github.guikeller.javafxhtml;

import com.github.guikeller.javafxhtml.reflection.HtmlAnalyser;
import com.google.inject.Injector;
import javafx.scene.web.WebEngine;

/**
 * JavaFX Html Bridge class for those using Java and Guice
 */
public class GuiceJavaFxHtml extends CoreJavaFxHtml {

    private Injector injector;

    /**
     * JavaFX Html Bridge class
     * @param webEngine JavaFX WebEngine
     * @param injector Google Guice 'Injector'
     */
    public GuiceJavaFxHtml(WebEngine webEngine, Injector injector) {
        super(webEngine);
        this.injector = injector;
    }

    /**
     * Invokes the given controller method
     * @param clazzName Controller fully qualified clazz name (eg: com.my.app.FooController.class)
     * @param methodName Method in the controller to be invoked (eg: saveFooInfo)
     * @return Object (recommended String formatted in JSON)
     */
    @Override
    public Object invokeController(String clazzName, String methodName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Object guiceManagedControllerInstance = this.injector.getInstance(clazz);
            HtmlAnalyser analyser = new HtmlAnalyser(super.webEngine);
            return analyser.invokeController(guiceManagedControllerInstance, methodName);
        } catch (Exception ex) {
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

}
