package br.com.dbengine.springb4.controller;
import br.com.dbengine.springb4.form.ImovelForm;
import br.com.dbengine.springb4.form.PeopleForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ImovelRestController {
    @RequestMapping(value = "/imovelupdate1", method = RequestMethod.POST, consumes = "application/json")
    public void imovelUpdate(@RequestBody ImovelForm imovelForm,
                               HttpServletResponse response,
                               Model model) throws IOException {
        // funcionou ok para testes locais
        System.out.println("ImovelRestController.Imovel : " + imovelForm.getImovel());
        System.out.println("ImovelRestController.Apelido : " + imovelForm.getApelido());
        model.addAttribute("imovel", imovelForm.getImovel());
        model.addAttribute("apelido", imovelForm.getApelido());
        //response.sendRedirect("/imovel/update");
        response.setHeader("Location", "/imovel/update");
        response.setStatus(302);
        //return "OK";
    }

    @RequestMapping(value = "/imovelupdate2", method = RequestMethod.POST, consumes = "application/json")
    public String showOwner(@RequestBody ImovelForm imovelForm) {
        ModelAndView mav = new ModelAndView("/imovel/update");
        //mav.addObject(this.clinicService.findOwnerById(ownerId));
        System.out.println("ImovelRestController.Imovel : " + imovelForm.getImovel());
        System.out.println("ImovelRestController.Apelido : " + imovelForm.getApelido());
        //mav.addAttribute("imovel", imovelForm.getImovel());
        //mav.addAttribute("apelido", imovelForm.getApelido());
        mav.addObject(imovelForm);
        //return mav;
        return "redirect:/imovel/update";
    }

}
