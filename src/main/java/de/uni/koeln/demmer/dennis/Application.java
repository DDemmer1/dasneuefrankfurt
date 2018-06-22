package de.uni.koeln.demmer.dennis;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;


import de.uni.koeln.demmer.dennis.model.lucene.LuceneUtil;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

//@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        LuceneUtil luceneUtil = new LuceneUtil();

        try {
            luceneUtil.wordInIndex("Gefellfchaft");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


}




