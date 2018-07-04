package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import java.util.ArrayList;
import java.util.List;

public class CharacterChanger {


    private char oldChar;
    private char newChar;

    public CharacterChanger(Character oldChar, Character newChar){
        this.oldChar = oldChar;
        this.newChar = newChar;
    }

    /**
     * Erzeugt alle moeglichen Kombinationen an Strings, bei denen 'oldChar' mit 'newChar' getauscht wurde
     * @param token Das Token, das den zu bearbeitenden String enthaelt
     * @return Eine List<String> mit allen Kombinationen der getauschten chars
     */
    public List<String> getAllCombinations(Token token) {

        //Position der f's im String erfassen
        List<Integer> posF = new ArrayList<>();
        for (int i = 0; i < token.getOrigin().length(); i++) {
            char current = token.getOrigin().charAt(i);
            if (current == oldChar) {
                posF.add(i);
            }
        }

        //Binary Combos erzeugen
        List<List<Integer>> combos = generateBinaryCombo(posF.size());

        //Combos auf den String mappen
        return mapCombosOnString(combos, posF, token);
    }

    private List<List<Integer>> generateBinaryCombo(int n) {

        List<List<Integer>> combos = new ArrayList<>();

        for (int i = 0; i < Math.pow(2, n); i++) {
            List<Integer> combo = new ArrayList<>();
            String format = "%0" + n + "d";
            String x = String.format(format, Integer.valueOf(Integer.toBinaryString(i)));

            char[] chars = x.toCharArray();
            for (char c : chars) {
                combo.add(Integer.valueOf(Character.toString(c)));
            }
            combos.add(combo);
        }

        return combos;
    }


    private List<String> mapCombosOnString(List<List<Integer>> combos, List<Integer> posF, Token token) {
        List<String> results = new ArrayList<>();

        //ueber alle combos laufen
        for (List<Integer> combo : combos) {
            //Char array zum bearbeiten erstellen
            char[] charArray = token.getOrigin().toCharArray();
            //ueber einzelne combo laufen
            for (int i = 0; i < combo.size(); i++) {
                //wenn in der Combo 1 auftaucht
                if (combo.get(i) == 1) {
                    //aendere an der vorher ermittelten position f zu s
                    charArray[posF.get(i)] = newChar;
                }
            }
            String newString = new String(charArray);
            results.add(newString);
        }
        return results;
    }
}
