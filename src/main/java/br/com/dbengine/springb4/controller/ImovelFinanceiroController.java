package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.ImovelDAO;
import br.com.dbengine.springb4.form.ImovelOcorrForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ImovelFinanceiroController {

    @GetMapping("/imovelFinanceiroDetail")
    public String imovelFinanceiroDetail(Model model, @RequestParam String imovelId) {
        //List<ImovelOcorrForm> iOccListForm = dao.getListForm(imovelId);
        // Descriçáo do Imovel
        String imovelDescr = new ImovelDAO().getItem(imovelId).getImovel();

        model.addAttribute("imovelIdAttr",imovelId);
        model.addAttribute("imovelIdDescr",imovelDescr);
        //model.addAttribute("imovelOccList",iOccListForm);
        return "imovelFinanceiro/detail";
    }

}
