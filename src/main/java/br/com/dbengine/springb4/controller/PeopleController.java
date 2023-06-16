package br.com.dbengine.springb4.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

//import com.example.dto.PeriodoDTO;

@Controller
public class PeopleController {

    @GetMapping("/people")
    public String people() {
        return "people";
    }

    @RequestMapping(value="/people/refresh")
    public String peopleRefresh(RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addAttribute("refresh", "true");
        //redirectAttributes.addFlashAttribute("fa", faValue);
        return "redirect:/people";
    }

    @GetMapping("/people/add")
    public String peopleAdd() {
        return "peopleAdd";
    }

    @GetMapping("/people/update")
    public String peopleUpdate(@RequestParam(name="id") String id,
                               @RequestParam(name="codigoInterno") String codigoInterno,
                               @RequestParam(name="nome") String name,
                               @RequestParam(name="dataNascimento") String dataNascimento,
                               @RequestParam(name="observacao") String observacao,
                               Model model) {
        //model.addAttribute("name", name);
        //System.out.println("id " + id );
        //System.out.println("Nome ANTES decode: " + name );
        //System.out.println("codigoInterno " + codigoInterno );
        name = this.decode(name);
        //System.out.println("Nome DEPOIS decode: " + name );
        model.addAttribute("peopleId", id);
        model.addAttribute("name", name);
        model.addAttribute("dataNascimento", dataNascimento);
        model.addAttribute("observacao", observacao);
        model.addAttribute("codigoInterno", codigoInterno);
        return "peopleUpdate";
    }

    private String decode(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
