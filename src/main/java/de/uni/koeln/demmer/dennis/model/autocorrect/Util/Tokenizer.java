package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Tokenizer {


    /**
     * Tokanisiert einen String in @{@link Token} tokenisieren, die nur den Wort Ursprung vermerkt haben.
     *
     * @param text Der zu tokenisierende Text
     * @return Eine Liste mit Tokens
     */
    public static List<Token> tokenize(String text) {
        List<Token> tokens = new ArrayList<>();

        BreakIterator iterator = BreakIterator.getWordInstance(Locale.GERMAN);
        iterator.setText(text);
        int start = iterator.first();
        int end = iterator.next();

        while (end != BreakIterator.DONE) {


            String currentWord = text.substring(start, end);

            if (Character.isLetter(currentWord.charAt(0)) && currentWord.length() >= 1) {

                Token token = new Token(currentWord);

                token.setStart(start);
                token.setEnd(end);

                if (currentWord.contains("f")) {
                    token.getfToS().addAll(new CharacterChanger('f', 's').getAllCombinations(token));
                }
//                if (currentWord.equals(System.getProperty("line.separator")) || currentWord.equals("\t") || currentWord.equals(" ")) {
//                    token.setBlank(true);
//                }
                tokens.add(token);


            }
            start = end;
            end = iterator.next();
        }

        return tokens;

    }


}
