package de.uni.koeln.demmer.dennis.model.Util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextProcessor {


    public List<String> changeCharacter(Token token,char character){


        List<String> results = new ArrayList<>();






        return results;


    }

    public String getTextFromTokens(List<Token> tokens){

        StringBuffer text = new StringBuffer();

        for (Token token: tokens) {
            if(token.getMostSimiliar().size()>0 && token.getOrigin().length()>1){
                text.append(token.getMostSimiliar().toArray()[0]);
            } else if(token.isSpecialChar()){
                text.append(token.getOrigin());
            } else if(token.getOrigin().length()>1){
                text.append(token.getOrigin());
            }
        }
        return text.toString();
    }



    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }



}
