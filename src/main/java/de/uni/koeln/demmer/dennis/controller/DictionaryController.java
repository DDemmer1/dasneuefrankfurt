package de.uni.koeln.demmer.dennis.controller;

import de.uni.koeln.demmer.dennis.model.autocorrect.lucene.LuceneUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Controller um das Wörtebuch bzw. den Lucene-Index der Autokorrektur zu manipulieren
 *
 */
@CrossOrigin(maxAge = 3600)
@RestController
public class DictionaryController {

    /**
     * Fügt dem Index ein Wort hinzu. Es wird ein Authentifizierungstoken benötigt.
     *
     * @param word Das hinzuzufügende Wort
     * @param auth Authentifizierungstoken
     * @throws IOException
     */
    @RequestMapping(path = "/addword", produces = "application/text", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addWord(@RequestParam("word") String word, @RequestParam("auth") String auth) throws IOException {


//        curl -i -d word='word' -d auth='spinfo' -X POST http://localhost:9090/addword


        Logger logger = LoggerFactory.getLogger(DictionaryController.class);


        //TODO Token Auslagern in properties Datei auf dem Server
        if(auth.equals("spinfo")){
            new LuceneUtil().addWBEntry(word);
            logger.info("Added 'Word: " + word + "' to dictionary with 'Authentification: '" + auth);
        } else {
            logger.warn("Authentification: '" + auth + "'. Access denied.");
            throw new IllegalArgumentException();
        }

    }


    @ExceptionHandler(IllegalArgumentException.class)
    private void badRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }




}
