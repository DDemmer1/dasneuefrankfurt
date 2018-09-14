package de.uni.koeln.demmer.dennis.model.ner.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheironParser {


    public List<NamedEntity> parseCheironXML(File cheironXML) {

        try {
            List<NamedEntity> namedEntities = new ArrayList<>();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(cheironXML);

            doc.getDocumentElement().normalize();

            NodeList entityNodes = doc.getElementsByTagName("types:EntityMention");
            Element textNodes = (Element) doc.getElementsByTagName("cas:Sofa").item(1);

            String text = textNodes.getAttribute("sofaString");


            for (int i = 0; i < entityNodes.getLength(); i++) {
                NamedEntity ne = new NamedEntity();

                Node nerNode = entityNodes.item(i);
                if (nerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nerNode;

                    int begin = Integer.valueOf(element.getAttribute("begin"));
                    int end = Integer.valueOf(element.getAttribute("end"));
                    ne.setEnd(end);
                    ne.setStart(begin);
                    ne.setEntity(text.substring(begin,end));

                    namedEntities.add(ne);
                }
            }

            return namedEntities;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
