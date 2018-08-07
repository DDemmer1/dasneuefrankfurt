package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import de.uni.koeln.demmer.dennis.model.autocorrect.lucene.LuceneUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TokenProcessor {



    public List<Token> process(List<Token> tokenList){

        LuceneUtil util = new LuceneUtil();

        for (Token token : tokenList) {
            util.processToken(token);
//            System.out.println(token.toString());
//            System.out.println(ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        }


        return tokenList;
    }

//    public String autoCorrect(String origin) {
//
//        Tokenizer tokenizer = new Tokenizer();
//        List<Token> tokenList = tokenizer.tokenize(origin);
//
//        LuceneUtil util = new LuceneUtil();
//
//        for (Token token : tokenList) {
//            util.processToken(token);
////            System.out.println(token.toString());
////            System.out.println(ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
//        }
//
////        tokenList = preProcessTokenList(tokenList);
////        tokenList = preProcessTokenList(tokenList);
//
//        return Autocorrecter.getAutocorrectText(tokenList);
//    }


    public String getTextFromTokens(List<Token> tokens) {

        StringBuffer text = new StringBuffer();

        for (Token token : tokens) {
//            if (token.getMostSimiliar().size() > 0 ) {
            if (token.getMostSimiliar().size() > 0 && token.isInWB()) {
                text.append(token.getMostSimiliar().toArray()[0]);
            } else {
                text.append(token.getOrigin());
            }
        }
        return text.toString();
    }


//    private List<Token> preProcessTokenList(List<Token> tokenList) {
//
//
//        for (int i = 0; i < tokenList.size() - 1; i++) {
//            Token current = tokenList.get(i);
//            Token next = tokenList.get(i + 1);
//
//            boolean twoBlanksInARow = current.isBlank() && next.isBlank();
//            boolean twoSpecCharsInARow = current.isSpecialChar() && next.isSpecialChar();
//            boolean singleChar = (current.getOrigin().length()< 2) && (!current.isSpecialChar() && !current.isBlank());
//            boolean isUncommonChar = current.isSpecialChar() && current.getOrigin().matches("[+=¦@#|'<>^*%]");
//
//            if(singleChar){
//                tokenList.remove(i);
//                continue;
//            } else if(isUncommonChar){
//                tokenList.remove(i);
//                continue;
//            }
//            if (twoSpecCharsInARow || twoBlanksInARow) {
//                tokenList.remove(i);
//            }
//        }
//        return tokenList;
//    }




}
