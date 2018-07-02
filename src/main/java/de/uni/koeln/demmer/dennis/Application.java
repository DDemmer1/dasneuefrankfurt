package de.uni.koeln.demmer.dennis;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;


import de.uni.koeln.demmer.dennis.model.Util.TextProcessor;
import de.uni.koeln.demmer.dennis.model.Util.Token;
import de.uni.koeln.demmer.dennis.model.Util.Tokenizer;
import de.uni.koeln.demmer.dennis.model.lucene.LuceneUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

//@SpringBootApplication
public class Application {


    public static void main(String[] args) {

        String origin ="";
        String goldstd ="";
        try {
            origin = TextProcessor.readFile("origin.txt", StandardCharsets.ISO_8859_1);
            goldstd = TextProcessor.readFile("goldstd.txt", StandardCharsets.ISO_8859_1);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        Token testToken = new Token("fppfppfppfppfppf");


        TextProcessor txtProc = new TextProcessor();
        Tokenizer tokenizer = new Tokenizer();

        List<Token> tokenList = tokenizer.tokenize(origin);

        LuceneUtil util = new LuceneUtil();


        for (Token token : tokenList) {

            util.processToken(token);

            System.out.println(token.toString());

        }

        System.out.println(txtProc.getTextFromTokens(tokenList));


    }


}




