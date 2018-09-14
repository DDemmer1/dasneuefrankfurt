package de.uni.koeln.demmer.dennis.controller;


import Cheiron.Cheiron;
import DnfWrapper.DNFUtil;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.TextUtil;
import de.uni.koeln.demmer.dennis.model.ner.util.CheironParser;
import de.uni.koeln.demmer.dennis.model.ner.util.NamedEntity;
import de.uni.koeln.demmer.dennis.model.ner.util.NerXmlBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
public class NERcontroller {


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

        return TextUtil.readFile(new File("data/out/text.xml").getPath(), StandardCharsets.UTF_8);
    }

}
