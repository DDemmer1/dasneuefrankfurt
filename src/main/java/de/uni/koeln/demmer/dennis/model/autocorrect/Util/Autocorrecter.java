package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import edu.stanford.nlp.ling.tokensregex.PhraseTable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.List;

public class Autocorrecter {

    public Token getBestGuess(Token token) {

        //TODO hmm implementation

        return token;

    }


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
//              token.setOrigin((String)token.getMostSimiliar().toArray()[0]);
            }
        }


        XMLBuilder xmlBuilder = new XMLBuilder(tokenList);


        Document xml = xmlBuilder.buildXML();

        String result = xml.getDocumentElement().getTextContent();


        return result;
    }

}
