package br.com.dbengine.springb4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(path="/api/test")
public class HelloController {

    // FUNCIONA, MAS GEROU CUSTO NO GAE - Google Cloud Storage
    //private static final Logger log = Logger.getLogger(HelloController.class.getName());

    @GetMapping("/dog/{name}")
    public String hello(@PathVariable String name) {
        //log.info("Dog: " + name);
        return "Hello World - " + name;
    }

}