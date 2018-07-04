package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import de.uni.koeln.demmer.dennis.model.autocorrect.lucene.LuceneUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextProcessor {

    public String autoCorrect(String origin){

        Tokenizer tokenizer = new Tokenizer();

        List<Token> tokenList = tokenizer.tokenize(origin);

        LuceneUtil util = new LuceneUtil();


        for (Token token : tokenList) {
            util.processToken(token);
//            System.out.println(token.toString());
        }

        tokenList = preProcessTokenList(tokenList);

        return getTextFromTokens(tokenList);
    }


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


    private List<Token> preProcessTokenList(List<Token> tokenList){

        for (int i = 0; i < tokenList.size()-1; i++) {
           Token current =  tokenList.get(i);
           Token next = tokenList.get(i+1);

           if(current.isSpecialChar() && current.getOrigin().equals(next.getOrigin())){
//           if(current.isSpecialChar() && next.isSpecialChar()){
                tokenList.remove(i);

           }
        }
        return tokenList;
    }



    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }



}
