package my.sample.app.spring.ui.model;

import com.github.guikeller.javafxhtml.annotations.FxHtmlField;
import com.github.guikeller.javafxhtml.converters.DateConverter;
import com.github.guikeller.javafxhtml.converters.IntegerConverter;

import java.time.Instant;

public class SpringSampleModel {

    @FxHtmlField(elementId = "id", converter = IntegerConverter.class)
    private Integer id;

    @FxHtmlField(elementId = "value")
    private String value;

    @FxHtmlField(elementId = "today", converter = DateConverter.class)
    private Instant today;

    private String fooBar;

    public SpringSampleModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Instant getToday() {
        return today;
    }

    public void setToday(Instant today) {
        this.today = today;
    }

    public String getFooBar() {
        return fooBar;
    }

    public void setFooBar(String fooBar) {
        this.fooBar = fooBar;
    }

}
