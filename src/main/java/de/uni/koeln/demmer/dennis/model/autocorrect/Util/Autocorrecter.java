package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.List;

public class Autocorrecter {

    public Token getBestGuess(Token token){

        //TODO hmm implementation

        return token;

    }


    public Document autocorrect(Document doc){


        for (int i = 0; i < doc.getElementsByTagName("word").getLength(); i++) {

            Node word = doc.getElementsByTagName("word").item(i);

            if(word.getAttributes().getLength()>=2){
                String bestGuess = word.getAttributes().item(0).getTextContent();
                doc.getElementsByTagName("word").item(i).setTextContent(bestGuess);
            }
        }
        return doc;
    }

}
