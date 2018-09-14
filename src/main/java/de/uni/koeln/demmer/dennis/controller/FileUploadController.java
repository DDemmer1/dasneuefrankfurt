package de.uni.koeln.demmer.dennis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import Cheiron.Cheiron;
import DnfWrapper.DNFUtil;
import de.uni.koeln.demmer.dennis.controller.storage.StorageFileNotFoundException;
import de.uni.koeln.demmer.dennis.controller.storage.StorageService;
import de.uni.koeln.demmer.dennis.model.autocorrect.Util.*;
import de.uni.koeln.demmer.dennis.model.ner.util.CheironParser;
import de.uni.koeln.demmer.dennis.model.ner.util.NamedEntity;
import de.uni.koeln.demmer.dennis.model.ner.util.NerXmlBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Text;


@CrossOrigin(maxAge = 3600)
@RestController
public class FileUploadController {

//    private final StorageService storageService;

//    @Autowired
//    public FileUploadController(StorageService storageService) {
//        this.storageService = storageService;
//    }


    @PostMapping("/uploadtxt")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException {



        File convFile = FileConverter.convertToFile(file);

        String input ="";
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

        XMLBuilder xmlBuilder = new XMLBuilder(tokenList);
        Document xml = xmlBuilder.buildXML();

        return TextUtil.readFile(xmlBuilder.getResult().getPath(),StandardCharsets.UTF_8);

    }



    @PostMapping("/uploadzip")
    public ResponseEntity<Resource> handleZIPUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {


        File convFile = FileConverter.convertToFile(file);
        List<File> unpacked = FileConverter.unpackZIP(convFile);


        List<File> results = new ArrayList<>();


        int i = 0;
        for (File unpackedFile: unpacked) {
            String text = TextUtil.readFile(unpackedFile.getPath(),StandardCharsets.UTF_8);

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
            nerXmlBuilder.buildXML(nerList,text);

            File xmloutput = new File("data/out/text.xml");
            File toZIP = new File("data/ner/text"+i+".xml");
            Files.copy(xmloutput.toPath(), toZIP.toPath());
            i++;
        }

        for (int j = 0; j < i; j++) {
            results.add(new File("data/ner/text"+j+".xml"));
        }

        File resultZIP = FileConverter.convertTozip(results);

        HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION
                ,"attachment; filename=result.zip");
        Path path = Paths.get(resultZIP.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resultZIP.length())
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(resource);
    }






//
//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }




}
