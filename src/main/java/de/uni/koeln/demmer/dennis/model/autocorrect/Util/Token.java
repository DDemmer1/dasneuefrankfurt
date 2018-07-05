package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import java.util.*;

public class Token {

    private String origin;
    private Set<String> mostSimiliar;
    private List<String> fToS;
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
        fToS = new ArrayList<>();

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
        fToS = new ArrayList<>();
        this.origin = origin;
        this.inWB = inWB;
        this.isBlank = isBlank;
        this.isSpecialChar = isSpecialChar();
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

    public List<String> getfToS() {
        return fToS;
    }

    public void setfToS(List<String> fToS) {
        this.fToS = fToS;
    }

    @Override
    public String toString() {
        return "Token{" +
                "origin='" + origin + '\'' +
                ", inWB=" + inWB +
                ", isBlank=" + isBlank +
                ", isSpecialChar=" + isSpecialChar +
                ", mostSimiliar=" + mostSimiliar.toString() +
                ", fToS=" + fToS.toString() +
                '}';
    }
}
