package de.uni.koeln.demmer.dennis.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;

/**
 * Controller Klasse für die Info Seite der REST API
 */
@Controller
public class HomeController {

    /**
     * Request mapping auf '/'. Zeigt eine HTML Datei an die auf die einzelnen Methoden verweist.
     *
     * @return index.html
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String home(){

        return "index";
    }


}
