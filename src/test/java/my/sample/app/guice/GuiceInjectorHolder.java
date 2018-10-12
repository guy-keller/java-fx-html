package my.sample.app.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceInjectorHolder {

    private static final Injector INJECTOR = Guice.createInjector(new JavaFxGuiceModule());

    public static Injector getInjector() {
        return INJECTOR;
    }

    private static final class JavaFxGuiceModule extends AbstractModule {
        @Override
        public void configure() {
            // NO-OP, but could do lets say..
            // bind(MyInterface.class).to(MyInterfaceImpl.class);
        }
    }

}
