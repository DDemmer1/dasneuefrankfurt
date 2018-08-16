package de.uni.koeln.demmer.dennis.controller;


import de.uni.koeln.demmer.dennis.model.autocorrect.Util.*;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
public class AutocorrectController {

    @RequestMapping(path = "/test", produces = "application/xml", method = RequestMethod.POST)
    public String test() throws IOException {

        String origin = "";
        String goldstd = "";
        try {
            origin = TextUtil.readFile("origin.txt", StandardCharsets.ISO_8859_1);
            goldstd = TextUtil.readFile("goldstd.txt", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }



        TextPreProcessor prePro = new TextPreProcessor(origin);
        String text = TextPreProcessor.getText();


        List<Token> tokenList = Tokenizer.tokenize(text);

        TokenProcessor tokenProcessor = new TokenProcessor();
        tokenList = tokenProcessor.process(tokenList);

        XMLBuilder xmlBuilder = new XMLBuilder(tokenList);
        Document xml = xmlBuilder.buildXML();


        return TextUtil.readFile(xmlBuilder.getResult().getPath(),StandardCharsets.UTF_8);

    }



    @RequestMapping(path = "/correct", produces = "application/xml", method = RequestMethod.POST)
    public String correct(@RequestBody  String input) throws IOException {

        System.out.println(input);
        TextPreProcessor prePro = new TextPreProcessor(input);
        String text = TextPreProcessor.getText();


        List<Token> tokenList = Tokenizer.tokenize(text);

        TokenProcessor tokenProcessor = new TokenProcessor();
        tokenList = tokenProcessor.process(tokenList);

        XMLBuilder xmlBuilder = new XMLBuilder(tokenList);
        Document xml = xmlBuilder.buildXML();

        return TextUtil.readFile(xmlBuilder.getResult().getPath(),StandardCharsets.UTF_8);
    }


}
