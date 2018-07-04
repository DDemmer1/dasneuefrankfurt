package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import java.util.LinkedHashSet;
import java.util.Set;

public class Token {

    private String origin;
    private Set<String> mostSimiliar;
    private  boolean inWB;
    private  boolean isBlank;
    private  boolean isSpecialChar;

    /**
     * Erzeugt ein @{@link Token}, nur mit einem Wortursprung.
     * @param origin
     */
    public Token(String origin){
        this.origin = origin;
        mostSimiliar = new LinkedHashSet<>();

    }

    /**
     * Erzeugt ein @{@link Token}
     * @param origin Das urspruengliche Wort
     * @param inWB Ob das Wort im Woeterbuch vorhanden ist
     * @param inWB Ob das Wort ein Leerzeichen ist
     * @param isSpecialChar Ob das Wort ein Sonderzeichen ist
     */
    public Token(String origin, boolean inWB, boolean isSpecialChar, boolean isBlank) {

        mostSimiliar = new LinkedHashSet<>();
        this.origin = origin;
        this.inWB = inWB;
        this.isBlank = isBlank;
        this.isSpecialChar = isSpecialChar();
        this.mostSimiliar = mostSimiliar;
    }

    public boolean isBlank() {
        return isBlank;
    }

    public void setBlank(boolean blank) {
        isBlank = blank;
    }

    public boolean isSpecialChar() {
        return isSpecialChar;
    }

    public void setSpecialChar(boolean specialChar) {
        isSpecialChar = specialChar;
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
                ", isSpecialChar=" + isSpecialChar +
                ", mostSimiliar=" + mostSimiliar.toString() +
                '}';
    }
}
