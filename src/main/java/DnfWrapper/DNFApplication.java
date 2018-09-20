package DnfWrapper;


import Cheiron.Cheiron;
import de.uni.koeln.demmer.dennis.model.ner.util.CheironParser;
import de.uni.koeln.demmer.dennis.model.ner.util.NamedEntity;
import de.uni.koeln.demmer.dennis.model.ner.util.NerXmlBuilder;

import java.io.File;
import java.util.List;


public class DNFApplication {

    public static void main(String[] params) throws Exception {

        Cheiron.findEntitys("Das ist ein Test von Dennis Demmer");
        DNFUtil.clearCheiron();

        String text ="Das ist ein Test von Dennis Demmer";

        NerXmlBuilder nerXmlBuilder = new NerXmlBuilder();
        List<NamedEntity> nerList = new CheironParser().parseCheironXML(new File("data/out/text.xml"));
        nerXmlBuilder.buildXML(nerList,text);

    }


}


