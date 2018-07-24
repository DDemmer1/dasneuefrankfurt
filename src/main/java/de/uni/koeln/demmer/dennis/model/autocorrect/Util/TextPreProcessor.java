package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.lucene.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextPreProcessor {


    private static String text;

    public TextPreProcessor(String text){
        this.text = concatSeperatedWords(text);
    }

    public static String getText(){

        if(text==null){
            System.err.println("TextPreProc not initialized!");
            return "";
        }

        return text;
    }


    private static String preProcessText(String text){

        text = concatSeperatedWords(text);




        return text;
    }




    private static String concatSeperatedWords(String text){


        char[] textArray = text.toCharArray();

        for (int i = 0; i < textArray.length-1; i++) {
            char current = textArray[i];
            char next = textArray[i+1];

            if (current == '-' && next =='\n'){
                textArray = ArrayUtils.remove(textArray, i);
                textArray = ArrayUtils.remove(textArray, i);

            }


        }

        return new String(textArray);

    }
}
