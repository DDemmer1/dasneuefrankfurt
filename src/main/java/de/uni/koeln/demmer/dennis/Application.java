package de.uni.koeln.demmer.dennis;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;


import de.uni.koeln.demmer.dennis.model.autocorrect.Util.CharacterChanger;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.TextProcessor;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.Token;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.Tokenizer;
import de.uni.koeln.demmer.dennis.model.autocorrect.lucene.CosineDocumentSimilarity;
import de.uni.koeln.demmer.dennis.model.autocorrect.lucene.LuceneUtil;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication
public class Application {


    public static void main(String[] args) {



        String word = "Gefellfchaft";

        CharacterChanger charChanger = new CharacterChanger('f','s');

        for (String combo : charChanger.getAllCombinations(new Token(word))) {
            System.out.println(combo);
        }

//        String origin ="";
//        String goldstd ="";
//        try {
//            origin = TextProcessor.readFile("origin.txt", StandardCharsets.ISO_8859_1);
//            goldstd = TextProcessor.readFile("goldstd.txt", StandardCharsets.ISO_8859_1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        TextProcessor txtProc = new TextProcessor();
//        String output = txtProc.autoCorrect(origin);
//
//
//        Double before = 0.9425723930238676;
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




