package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.Singleton.*;
import br.com.dbengine.springb4.dbUtil.Sysout;
import br.com.dbengine.springb4.entity.Cultura;
import br.com.dbengine.springb4.entity.Imovel;
import br.com.dbengine.springb4.form.ImovelForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ImovelController {

   @Autowired
    private ImovelDAO dao; // = new ImovelDAO();

    @GetMapping("/imovel")
    public String imovel() {
        return "imovel";
    }

    @RequestMapping(value="/imovel/refresh")
    public String imovelRefresh(RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addAttribute("refresh", "true");
        //redirectAttributes.addFlashAttribute("fa", faValue);
        return "redirect:/imovel";
    }

    @GetMapping("/imovelUpdate")
    public String imovelUpdate(@RequestParam(name="imovelForm") String imovelJSON,
                               Model mav) {
        //Sysout.s("ImovelController.imovelUpdate ..." + imovelJSON);
        String imovel, apelido;
        ObjectMapper oMapper = new ObjectMapper();
        ImovelForm imovelForm = null;
        try {
            imovelForm = oMapper.readValue(imovelJSON, ImovelForm.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        //Sysout.s("ImovelRestController.Imovel : " + imovelForm.getImovel());
        //Sysout.s("ImovelRestController.Apelido : " + imovelForm.getApelido());
        mav.addAttribute("imovel", imovelForm.getImovel());     //imovel);     //
        mav.addAttribute("apelido", imovelForm.getApelido());   //apelido);   //
        return "imovel/update";
    }

    @RequestMapping(value = "/imovelupdate",  consumes = "application/json")
    public String showOwner(@RequestBody ImovelForm imovelForm,
                            Model mav) {
        //Sysout.s("ImovelRestController.Imovel : " + imovelForm.getImovel());
        //Sysout.s("ImovelRestController.Apelido : " + imovelForm.getApelido());
        mav.addAttribute("imovel", imovelForm.getImovel());
        mav.addAttribute("apelido", imovelForm.getApelido());
        Sysout.s("Redirecting from Controller ... ");
        return "imovelUpdate";
    }

    // v2 - 31.08
    @GetMapping("/imovelList")
    public String imovelList(Model model) {
        List<Imovel> imovelList = new ArrayList<Imovel>();
        imovelList = dao.getList();
        //Sysout.s(">> " + imovelList.size());
        model.addAttribute("imovelList",imovelList);
        return "imovel/list";
    }

    @GetMapping("/imovelUpdForm")
    public String imovelUpdForm(@RequestParam int imovelId,Model model) {
        Sysout.s("imovelUpdForm...");
        Imovel imovelUpd = new Imovel();
        imovelUpd = dao.getItem(imovelId);
        //Sysout.s(" >> imovelUpdForm... " + imovelUpd.getApelido());
        model.addAttribute("imovel", imovelUpd);
        model.addAttribute("imobiliarias", new ImobiliariaDAO().getList());
        return "imovel/update";
    }

    @PostMapping("/imovelUpdate")
    public String imovelUpdate(@ModelAttribute Imovel imovel) {
        //Sysout.s("UPDATE imovel..." + imovel.getId());
        dao.update(imovel);
        return "redirect:/imovelList";
    }

    @GetMapping("/reloadImovelList")
    public String imovelUpdate() {
        ImovelListSingleton.setInstance(null);
        return "redirect:/imovelList";
    }
}
