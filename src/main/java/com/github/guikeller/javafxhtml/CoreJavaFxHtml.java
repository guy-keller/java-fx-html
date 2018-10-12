package com.github.guikeller.javafxhtml;

import com.github.guikeller.javafxhtml.interfaces.Command;
import com.github.guikeller.javafxhtml.reflection.HtmlAnalyser;
import com.github.guikeller.javafxhtml.reflection.HtmlPopulator;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * JavaFX Html Bridge class for those using only Java
 */
public class CoreJavaFxHtml {

    private static final String WINDOW = "window";
    protected WebEngine webEngine;

    /**
     * JavaFX Html Bridge class
     * @param webEngine JavaFX WebEngine
     */
    public CoreJavaFxHtml(WebEngine webEngine) {
        super();
        this.webEngine = webEngine;
    }

    /**
     * Invokes the given controller method
     * @param clazzName Controller fully qualified clazz name (eg: com.my.app.FooController.class)
     * @param methodName Method in the controller to be invoked (eg: saveFooInfo)
     * @return Object (recommended String formatted in JSON)
     */
    public Object invokeController(String clazzName, String methodName) {
        try {
            HtmlAnalyser analyser = new HtmlAnalyser(webEngine);
            Class<?> controllerClazz = Class.forName(clazzName);
            Object controllerInstance = controllerClazz.newInstance();
            return analyser.invokeController(controllerInstance, methodName);
        } catch (Exception ex) {
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    /**
     * Registers the JavaFX Html Bridge class with the given name in the HTML
     * @param memberId String memberId to be utilized in the JS 'window'
     */
    public void registerFxHtml(String memberId) {
        registerFxHtml(memberId, null);
    }

    /**
     * Registers the JavaFX Html Bridge class with the given name in the HTML
     * @param memberId String memberId to be utilized in the JS 'window'
     * @param controllerInstance
     */
    public void registerFxHtml(String memberId, Object controllerInstance) {
        waitForWebEngineIfRequiredThenExecute(() -> {
            registerController(memberId);
            populatePage(controllerInstance);
        });
    }

    protected void registerController(String memberId) {
        Object windowObj = webEngine.executeScript(WINDOW);
        JSObject window = (JSObject) windowObj;
        window.setMember(memberId, this);
    }

    protected void populatePage(Object controllerInstance) {
        if (controllerInstance != null) {
            try {
                HtmlPopulator populator = new HtmlPopulator(webEngine);
                populator.populatePage(controllerInstance);
            } catch (Exception ex) {
                throw new IllegalStateException(ex.getMessage(), ex);
            }
        }
    }

    protected void waitForWebEngineIfRequiredThenExecute(Command command) {
        if (webEngine.getLoadWorker().getState() == State.SUCCEEDED) {
            command.execute();
        } else {
            webEngine.getLoadWorker().stateProperty().addListener(
                    (observableValue, oldState, newState) -> {
                        if (newState == State.SUCCEEDED) {
                            command.execute();
                        }
                    });
        }
    }

}
