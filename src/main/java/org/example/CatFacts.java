package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatFacts {
    private String fact;

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    @Override
    public String toString() {
        return "CatFact{" +
                "fact='" + fact + '\'' +
                '}';
    }
}
