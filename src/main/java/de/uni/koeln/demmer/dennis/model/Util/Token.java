package de.uni.koeln.demmer.dennis.model.Util;

import java.util.LinkedHashSet;
import java.util.Set;

public class Token {

    private String origin;
    private  boolean inWB;
    private Set<String> mostSimiliar;


    public Token(String origin){
        this.origin = origin;
        mostSimiliar = new LinkedHashSet<>();

    }

    public Token(String origin, boolean inWB, Set<String> mostSimiliar) {

        mostSimiliar = new LinkedHashSet<>();
        this.origin = origin;
        this.inWB = inWB;
        this.mostSimiliar = mostSimiliar;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isInWB() {
        return inWB;
    }

    public void setInWB(boolean inWB) {
        this.inWB = inWB;
    }

    public Set<String> getMostSimiliar() {
        return mostSimiliar;
    }

    public void setMostSimiliar(Set<String> mostSimiliar) {
        this.mostSimiliar = mostSimiliar;
    }

    @Override
    public String toString() {
        return "Token{" +
                "origin='" + origin + '\'' +
                ", inWB=" + inWB +
                ", mostSimiliar=" + mostSimiliar.toString() +
                '}';
    }
}
