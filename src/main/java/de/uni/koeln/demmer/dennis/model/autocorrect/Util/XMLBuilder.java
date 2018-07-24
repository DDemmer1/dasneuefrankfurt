package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import org.w3c.dom.Document;

import java.util.List;

public class XMLBuilder {


    private List<Token> tokenList;
    private String text;

    public XMLBuilder(List<Token> tokenList){

        this.tokenList = tokenList;
        text = TextPreProcessor.getText();

    }



    public Document buildXML(){

//TODO

        return null;

    }


}
