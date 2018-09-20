package de.uni.koeln.demmer.dennis.controller;

import Cheiron.Cheiron;
import DnfWrapper.DNFUtil;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.*;
import de.uni.koeln.demmer.dennis.model.ner.util.CheironParser;
import de.uni.koeln.demmer.dennis.model.ner.util.NamedEntity;
import de.uni.koeln.demmer.dennis.model.ner.util.NerXmlBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * REST Controller für den Upload von Dateien für die Named-entity recognition.
 * Cross Origin ist aktiviert.
 */
@CrossOrigin(maxAge = 3600)
@RestController
public class FileUploadController {


    /**
     * Request mapping auf '/uploadtxt' für den Upload von Text-Dateien
     * Wenn eine Auto-Korrektur für Frakturfehler durchgeführt werden soll muss der parameter 'correct' gesetzt werden.
     * Wenn eine Named-entity recognition durchgeführt werden soll muss der Parameter 'ner' gesetzt werden.
     *
     * @param file MultipartFile mit einer Text Datei als Inhalt
     * @return Getaggte XML-Datei
     * @throws IOException
     */
    @PostMapping("/txt/{param}")
    public String handleFileUpload(@PathVariable("param") String param, @RequestParam("file") MultipartFile file) throws IOException {


        File convFile = FileConverter.convertToFile(file);


        if (param.equals("correct")) {

            String input = "";
            try {
                input = TextUtil.readFile(convFile.getPath(), StandardCharsets.ISO_8859_1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            TextPreProcessor prePro = new TextPreProcessor(input);
            String text = TextPreProcessor.getText();


            List<Token> tokenList = Tokenizer.tokenize(text);

            TokenProcessor tokenProcessor = new TokenProcessor();
            tokenList = tokenProcessor.process(tokenList);

            AutocorrectXMLBuilder autocorrectXmlBuilder = new AutocorrectXMLBuilder(tokenList);
            Document xml = autocorrectXmlBuilder.buildXML();
            return TextUtil.readFile(autocorrectXmlBuilder.getResult().getPath(), StandardCharsets.UTF_8);

        }



        else if(param.equals("ner")) {


            String text = TextUtil.readFile(convFile.getPath(), StandardCharsets.ISO_8859_1);

            //NER
            try {
                String fracturCorrected = new Autocorrecter().fracturCorrect(text);
                DNFUtil.clearCheiron();
                Cheiron.findEntitys(fracturCorrected);
            } catch (Exception e) {
                e.printStackTrace();
            }


            NerXmlBuilder nerXmlBuilder = new NerXmlBuilder();
            List<NamedEntity> nerList = new CheironParser().parseCheironXML(new File("data/out/text.xml"));
            nerXmlBuilder.buildXML(nerList, text);

            File xmloutput = new File("data/ner/taggedNER.xml");


            return TextUtil.readFile(xmloutput.getPath(), StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException();
        }

    }


    /**
     * Request mapping auf '/zip/{param}' um ZIP Dateien mit Textinhalten hochzuladen.
     * Um eine automatische Frakturfehler Korrektur zu aktivieren muss der Parameter auf 'true' gesetzt werden.
     * Um eine automatische Frakturfehler Korrektur zu deaktivieren muss der Parameter auf 'false' gesetzt werden.
     *
     * Es wird eine gepackte ZIP Datei zurückgegeben die getaggte XML Dateien enthält.
     *
     * @param param
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/zip/{param}")
    public ResponseEntity<Resource> handleZIPUpload(@PathVariable("param") String param,@RequestParam("file") MultipartFile file) throws IOException {

        if(!param.equals("false") && !param.equals("true")){
            throw new IllegalArgumentException();
        }


        File convFile = FileConverter.convertToFile(file);
        List<File> unpacked = FileConverter.unpackZIP(convFile);
        List<File> results = new ArrayList<>();


        int i = 0;
        for (File unpackedFile : unpacked) {
            String text = TextUtil.readFile(unpackedFile.getPath(), StandardCharsets.UTF_8);

            //NER
            try {
                if(param.equals("true")){
                    text = new Autocorrecter().fracturCorrect(text);
                }
                DNFUtil.clearCheiron();
                Cheiron.findEntitys(text);
            } catch (Exception e) {
                e.printStackTrace();
            }


            NerXmlBuilder nerXmlBuilder = new NerXmlBuilder();
            List<NamedEntity> nerList = new CheironParser().parseCheironXML(new File("data/out/text.xml"));
            nerXmlBuilder.buildXML(nerList, text);

            File xmloutput = new File("data/ner/taggedNER.xml");
            File toZIP = new File("data/ner/text" + i + ".xml");
            Files.copy(xmloutput.toPath(), toZIP.toPath());
            i++;
        }

        for (int j = 0; j < i; j++) {
            results.add(new File("data/ner/text" + j + ".xml"));
        }

        File resultZIP = FileConverter.convertTozip(results);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION
                , "attachment; filename=result.zip");
        Path path = Paths.get(resultZIP.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resultZIP.length())
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(resource);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    private void badRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}


