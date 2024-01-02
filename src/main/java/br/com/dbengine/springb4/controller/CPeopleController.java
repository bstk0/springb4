package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import br.com.dbengine.springb4.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//Create controller for handling HTTP requests
//@RestController
@Controller
public class CPeopleController {

    //Inject PersonDAO object
    @Autowired
    private CPeopleDAO dao;

    @GetMapping("/canonic1Add")
    public String canonic1Add(Model model) { //, @RequestParam Integer imovelId) {
        //ImovelOcorrencia imovOccAdd = new ImovelOcorrencia();
        //imovOccAdd.setImovel_id(imovelId);
        //model.addAttribute("imovelIdAttr",imovelId);
        CPeople cpeopleAdd = new CPeople();
        model.addAttribute("cpeople",cpeopleAdd);
        return "canonic1/add";
    }

    @PostMapping("/cpeopleSave")
    public String cpeopleSave(@ModelAttribute CPeople cpeople,
                                       Authentication authentication)  {
        //imovelOcorrencia.setCreatedBy(authentication.getName());
        dao.add(cpeople);
        Sysout.s(cpeople.getNome());
        //String s = "redirect:/imovelOcorrenciaList?imovelId=" + imovelOcorrencia.getImovel_id();
        String s = "redirect:/canonic1";
        return s;
    }

}
