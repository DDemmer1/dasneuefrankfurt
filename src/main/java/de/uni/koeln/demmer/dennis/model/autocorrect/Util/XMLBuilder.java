package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class XMLBuilder {


    private List<Token> tokenList;
    private String text;
    private File streamResult;

    public XMLBuilder(List<Token> tokenList) {
        this.tokenList = tokenList;
        text = TextPreProcessor.getText();
    }


    public Document buildXML() {

        int id = 0;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("text");
            doc.appendChild(rootElement);


            int start = 0;
            int end = 0;
            for (Token token : tokenList) {

                // word element
                Element word = doc.createElement("word");
                word.setTextContent(token.getOrigin());
                // set word attribute
                word.setAttribute("id", id + "");
                word.setAttribute("inWB", token.isInWB()+"");
                if(!token.getfToS().isEmpty()){
                    word.setAttribute("possibleFracture", "true");
                }

                StringBuffer buffer = new StringBuffer();

                if(!token.getMostSimiliar().isEmpty()){
                    word.setAttribute("bestGuess",(String)token.getMostSimiliar().toArray()[0]);


                    for (Object mostSimObj: token.getMostSimiliar().toArray()) {
                        String mostSim = (String) mostSimObj;
                        buffer.append(mostSim);
                        buffer.append(" ");
                    }
                    word.setAttribute("mostSimilar",buffer.toString().substring(0,buffer.toString().length()-1));

                }

                if(token.getOrigin().length()<2){
//                    word.setAttribute("deleteSuggestion","true");
                    word.setAttribute("bestGuess","");

                }

                start = token.getStart();
                //Add content between words
                String betweenWords = TextPreProcessor.getText().substring(end, start);

                String changedEncoding = new String(betweenWords.getBytes(), StandardCharsets.UTF_8);
                Text txt = doc.createTextNode(changedEncoding);

                //append text between words
                rootElement.appendChild(txt);
                //append word
                rootElement.appendChild(word);
                end = token.getEnd();

                id++;
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File xmlFile = new File("taggedText.xml");
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

            streamResult = xmlFile;

//            System.out.println(doc.getElementsByTagName("text").item(0).getTextContent());

            System.out.println("File saved!");
            return doc;
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        return null;

    }


    public File getResult(){


        return streamResult;

    }


}
