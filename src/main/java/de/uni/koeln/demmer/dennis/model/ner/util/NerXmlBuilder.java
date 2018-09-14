package de.uni.koeln.demmer.dennis.model.ner.util;

import de.uni.koeln.demmer.dennis.model.autocorrect.Util.TextPreProcessor;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.Token;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class NerXmlBuilder {


        public Document buildXML(List<NamedEntity> entityList,String text) {

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

                for (NamedEntity namedEntity : entityList) {

                    // word element
                    Element word = doc.createElement("namedentity");
                    word.setTextContent(namedEntity.getEntity());
                    word.setAttribute("id", id + "");

                    StringBuffer buffer = new StringBuffer();

                    start = namedEntity.getStart();

                    //Add content between words
                    String betweenWords = text.substring(end, start);

                    String changedEncoding = new String(betweenWords.getBytes(), StandardCharsets.UTF_8);
                    Text txt = doc.createTextNode(changedEncoding);

                    //append text between words
                    rootElement.appendChild(txt);
                    //append word
                    rootElement.appendChild(word);
                    end = namedEntity.getEnd();

                    id++;
                }



                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();

                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                DOMSource source = new DOMSource(doc);
                File xmlFile = new File("data/ner/taggedNER.xml");
                StreamResult result = new StreamResult(xmlFile);
                transformer.transform(source, result);

                return doc;
            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }

            return null;

    }


}
