package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.ImovelDAO;
import br.com.dbengine.springb4.DAO.ImovelFinanceiroDAO;
import br.com.dbengine.springb4.DAO.ImovelOcorrenciaDAO;
import br.com.dbengine.springb4.DAO.ReportsDAO;
import br.com.dbengine.springb4.dbUtil.Sysout;
import br.com.dbengine.springb4.entity.Imovel;
import br.com.dbengine.springb4.entity.ImovelFinanceiro;
import br.com.dbengine.springb4.entity.ImovelOcorrencia;
import br.com.dbengine.springb4.form.ImovelOcorrForm;
import br.com.dbengine.springb4.form.ImovelPagtoListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ImovelFinanceiroController {

    @Autowired
    private ImovelFinanceiroDAO dao; // = new ImovelOcorrenciaDAO();

    @GetMapping("/imovelFinanceiroDetail")
    public String imovelFinanceiroDetail(Model model, @RequestParam String imovelId) {
        //List<ImovelOcorrForm> iOccListForm = dao.getListForm(imovelId);
        ImovelFinanceiro iFin = dao.getItem(Integer.valueOf(imovelId));
        // Descriçáo do Imovel
        String imovelDescr = new ImovelDAO().getItem(imovelId).getImovel();

        model.addAttribute("imovelIdAttr",imovelId);
        model.addAttribute("imovelIdDescr",imovelDescr);
        model.addAttribute("imovelFinanceiro", iFin);
        return "imovelFinanceiro/detail";
    }

    @PostMapping("/imovelFinancUpdate")
    public String imovelFinancUpdate(@ModelAttribute ImovelFinanceiro imovelFinanceiro,
                                  Authentication authentication) {
        Sysout.s("UPDATE imovelFinanceiro..." + imovelFinanceiro.getImovel_id());
        imovelFinanceiro.setUpdatedBy(authentication.getName());
        dao.update(imovelFinanceiro);
        //String redirect = "redirect:/imovelOcorrenciaList?imovelId=" + imovelFinanceiro.getImovel_id();
        String redirect = "redirect:/imovelList";
        return redirect;
    }

    @GetMapping("/imovelPagtoList")
    public String imovelPagtoList(Model model) {
        List<ImovelPagtoListForm> imovelPagtoList = new ArrayList<ImovelPagtoListForm>();
        imovelPagtoList = new ReportsDAO().getImovelPagtosList();
        model.addAttribute("imovelPagtoList",imovelPagtoList);
        return "imovelFinanceiro/pagtoList";
    }
}