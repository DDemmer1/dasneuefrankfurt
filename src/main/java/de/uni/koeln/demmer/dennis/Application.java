package de.uni.koeln.demmer.dennis;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;


import de.uni.koeln.demmer.dennis.model.autocorrect.Util.*;
import de.uni.koeln.demmer.dennis.model.autocorrect.lucene.LuceneUtil;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication
public class Application {


    public static void main(String[] args) {


        String origin = "";
        String goldstd = "";
        try {
            origin = TextUtil.readFile("origin.txt", StandardCharsets.ISO_8859_1);
            goldstd = TextUtil.readFile("goldstd.txt", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

//
        TextPreProcessor prePro = new TextPreProcessor(origin);
        String text = TextPreProcessor.getText();


        List<Token> tokenList = Tokenizer.tokenize(text);

        TokenProcessor tokenProcessor = new TokenProcessor();
        tokenList = tokenProcessor.process(tokenList);

        Autocorrecter autocorrecter = new Autocorrecter();





//        System.out.println(output);
//        Double before = 0.9622705744219966;
//        System.out.println("Before: \t" + before);
//        try {
//            double sim = CosineDocumentSimilarity.getCosineSimilarity(goldstd , output);
//            System.out.println("After: \t\t" + sim);
//            System.out.println("******************************");
//            System.out.println("Difference: " + (before - sim));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }


}




