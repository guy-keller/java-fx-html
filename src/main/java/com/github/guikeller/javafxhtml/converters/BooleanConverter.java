package com.github.guikeller.javafxhtml.converters;

public class BooleanConverter implements Converter<Boolean> {

    public BooleanConverter() {
        super();
    }

    @Override
    public Boolean convert(Object obj) {
        String value = (String) obj;
        return Boolean.valueOf(value.toUpperCase());
    }

    @Override
    public String asString(Object obj) {
        Boolean value = (Boolean) obj;
        return Boolean.toString(value);
    }

}
