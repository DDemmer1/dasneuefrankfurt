package de.uni.koeln.demmer.dennis.controller;


import Cheiron.Cheiron;
import DnfWrapper.DNFUtil;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.TextUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@CrossOrigin(maxAge = 3600)
@RestController
public class NERcontroller {


    @RequestMapping(path = "/ner", produces = "application/xml", method = RequestMethod.POST)
    public String ner(@RequestBody  String text) throws IOException {

        try {





            DNFUtil.clearCheiron();
            Cheiron.findEntitys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TextUtil.readFile(new File("data/out/text.xml").getPath(), StandardCharsets.UTF_8);
    }

}
