package my.sample.app.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

@Configuration
@ComponentScan
public class SpringContextHolder {

    private static final AbstractApplicationContext CONTEXT = new AnnotationConfigApplicationContext(SpringContextHolder.class);

    public static ApplicationContext getApplicationContext() {
        return CONTEXT;
    }

}
