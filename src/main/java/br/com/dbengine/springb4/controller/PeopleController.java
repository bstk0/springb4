package br.com.dbengine.springb4.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.example.dto.PeriodoDTO;

@Controller
public class PeopleController {

    @GetMapping("/people")
    public String people() {
        return "people";
    }

    @GetMapping("/people/add")
    public String peopleAdd() {
        return "peopleAdd";
    }

    @GetMapping("/people/update")
    public String peopleUpdate(@RequestParam(name="Nome") String name,
                               @RequestParam(name="codigoInterno") String codigoInterno,
                               @RequestParam(name="id") String id,
                               Model model) {
        //model.addAttribute("name", name);
        //System.out.println("id " + id );
        //System.out.println("Nome " + name );
        //System.out.println("codigoInterno " + codigoInterno );
        model.addAttribute("peopleId", id);
        model.addAttribute("name", name);
        model.addAttribute("codigoInterno", codigoInterno);
        return "peopleUpdate";
    }

}
