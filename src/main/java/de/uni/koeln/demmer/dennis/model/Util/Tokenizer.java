package de.uni.koeln.demmer.dennis.model.Util;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class Tokenizer {

    public List<Token> tokenizeWithStringTokenizer(String text){

        List<Token> tokenList = new ArrayList<>();


        StringTokenizer st = new StringTokenizer(text);
        while (st.hasMoreTokens()) {
            tokenList.add(new Token(st.nextToken()));
        }

        return tokenList;
    }



    public List<Token> tokenizeWithSplit(String text){

        List<Token> tokenList = new ArrayList<>();

        String[] tokenArray = text.split("/w");

        for (String token : tokenArray) {
            tokenList.add(new Token(token));

        }

        return tokenList;
    }



    public static List<Token> tokenize(String text) {
        List<Token> tokens = new ArrayList<>();

        text = text.toLowerCase(Locale.GERMAN);

        BreakIterator iterator = BreakIterator.getWordInstance(Locale.GERMAN);
        iterator.setText(text);
        int start = iterator.first();
        int end = iterator.next();

        while (end != BreakIterator.DONE) {
            String currentWord = text.substring(start, end);

            if (Character.isLetterOrDigit(currentWord.charAt(0)) && currentWord.length() >= 1) {

                tokens.add(new Token(currentWord));
            }

            start = end;
            end = iterator.next();

        }

        return tokens;

    }




}
