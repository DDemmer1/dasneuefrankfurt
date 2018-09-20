package de.uni.koeln.demmer.dennis.controller;


import Cheiron.Cheiron;
import DnfWrapper.DNFUtil;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.TextUtil;
import de.uni.koeln.demmer.dennis.model.ner.util.CheironParser;
import de.uni.koeln.demmer.dennis.model.ner.util.NamedEntity;
import de.uni.koeln.demmer.dennis.model.ner.util.NerXmlBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * Controller für die Named-entity recognition
 *
 */
@CrossOrigin(maxAge = 3600)
@RestController
public class NERcontroller {


    /**
     * Mapping auf '/ner'. Es wird eine named-entity recognition durchgeführt und eine getaggte XML Datei zurückgegeben
     *
     * @param text
     * @return
     * @throws IOException
     */
    @RequestMapping(path = "/ner", produces = "application/xml", method = RequestMethod.POST)
    public String ner(@RequestBody  String text) throws IOException {

        try {


            DNFUtil.clearCheiron();
            Cheiron.findEntitys(text);

            NerXmlBuilder nerXmlBuilder = new NerXmlBuilder();
            List<NamedEntity> nerList = new CheironParser().parseCheironXML(new File("data/out/text.xml"));
            nerXmlBuilder.buildXML(nerList,text);

            for (NamedEntity ne : nerList) {
                System.err.println(ne.getEntity());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return TextUtil.readFile(new File("data/ner/taggedNER.xml").getPath(), StandardCharsets.UTF_8);
    }

}
