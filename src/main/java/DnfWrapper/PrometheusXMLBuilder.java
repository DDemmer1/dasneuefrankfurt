package DnfWrapper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class PrometheusXMLBuilder {

    private File streamResult;


    public void buildXML(String text) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("result");
            doc.appendChild(rootElement);

            Element pid = doc.createElement("pid");
            pid.setTextContent("text");
            rootElement.appendChild(pid);

            Element description = doc.createElement("description");
            description.setTextContent(text);
            rootElement.appendChild(description);



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File xmlFile = new File("data/in/text.xml");
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);


            streamResult = xmlFile;

            System.out.println("File saved!");


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }


    }




    public File getResult(){


        return streamResult;

    }

}
