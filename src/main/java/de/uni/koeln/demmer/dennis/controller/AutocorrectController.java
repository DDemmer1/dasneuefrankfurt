package de.uni.koeln.demmer.dennis.controller;


import de.uni.koeln.demmer.dennis.model.autocorrect.Util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * REST Controller für die Autokorrektur.
 * Cross Origin ist aktiviert.
 *
 */
@CrossOrigin(maxAge = 3600)
@RestController
public class AutocorrectController {


    /**
     * Mapping für die Autokorrektur auf '/correct'.
     * Die temporäre output xml Datei wird in 'data/tmp/taggedText.xml' erzeugt.
     * Auf '/correct' ist ein Interceptor registriert, der die temporäre Datei nach dem Request löscht.
     *
     * @param input Der zu korrigierende String
     * @return Getaggter Text im XML Format
     * @throws IOException
     */
    @RequestMapping(path = "/correct", produces = "application/xml", method = RequestMethod.POST)
    public String correct(@RequestBody  String input) throws IOException {

        TextPreProcessor prePro = new TextPreProcessor(input);
        String text = TextPreProcessor.getText();

        List<Token> tokenList = Tokenizer.tokenize(text);

        TokenProcessor tokenProcessor = new TokenProcessor();
        tokenList = tokenProcessor.process(tokenList);

        AutocorrectXMLBuilder autocorrectXmlBuilder = new AutocorrectXMLBuilder(tokenList);
        Document xml = autocorrectXmlBuilder.buildXML();

        Logger logger = LoggerFactory.getLogger(AutocorrectController.class);
        logger.info("Autocorrect tagging complete");

        return TextUtil.readFile(autocorrectXmlBuilder.getResult().getPath(),StandardCharsets.UTF_8);
    }







}
