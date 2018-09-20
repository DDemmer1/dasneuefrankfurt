package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.List;

public class Autocorrecter {


    public Document autocorrect(Document doc) {


        for (int i = 0; i < doc.getElementsByTagName("word").getLength(); i++) {

            Node word = doc.getElementsByTagName("word").item(i);

            if (word.getAttributes().getLength() >= 2) {
                String bestGuess = word.getAttributes().item(0).getTextContent();
                doc.getElementsByTagName("word").item(i).setTextContent(bestGuess);
            }
        }
        return doc;
    }


    public String fracturCorrect(String text) {


        TextPreProcessor txtpre = new TextPreProcessor(text);
        text = TextPreProcessor.getText();


        List<Token> tokenList = Tokenizer.tokenize(text);

        TokenProcessor tokenProcessor = new TokenProcessor();
        tokenList = tokenProcessor.process(tokenList);


        for (Token token : tokenList) {
            if (!token.getMostSimiliar().isEmpty() && token.getOrigin().contains("f")) {
                token.setOrigin(token.getMostSimiliar().iterator().next());
            }
        }


        AutocorrectXMLBuilder autocorrectXmlBuilder = new AutocorrectXMLBuilder(tokenList);


        Document xml = autocorrectXmlBuilder.buildXML();

        String result = xml.getDocumentElement().getTextContent();


        return result;
    }

}
