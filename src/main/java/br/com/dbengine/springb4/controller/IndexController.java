package br.com.dbengine.springb4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.util.logging.Logger;

@RestController
//@RequestMapping(path="/")
public class IndexController {

    //private static final Logger log = Logger.getLogger(IndexController.class.getName());

    @GetMapping("/index/{name}")
    public String indexMethod(@PathVariable String name) {
        //log.info("Nome: " + name);
        //model.addAttribute("name", name);
        return "Index - " + name;
    }

}