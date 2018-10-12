package com.github.guikeller.javafxhtml.converters;

public class DoubleConverter implements Converter<Double> {

    public DoubleConverter() {
        super();
    }

    @Override
    public Double convert(Object obj) {
        String value = (String) obj;
        return Double.valueOf(value);
    }

    @Override
    public String asString(Object obj) {
        Double value = (Double) obj;
        return Double.toString(value);
    }

}
