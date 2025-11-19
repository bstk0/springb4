package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.*;
import br.com.dbengine.springb4.dbUtil.*;
import br.com.dbengine.springb4.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImovelFinanceiroController {

    @Autowired
    private HImovelFinancDAO dao; // = new ImovelOcorrenciaDAO();
    //private ImovelFinanceiroDAO dao; // = new ImovelOcorrenciaDAO();

    @GetMapping("/imovelFinanceiroDetail")
    public String imovelFinanceiroDetail(Model model, @RequestParam int imovelId) {
        ImovelFinanceiro iFin = dao.getItem(String.valueOf(imovelId));
        // Descriçáo do Imovel
        String imovelDescr = "TESTE " ; //new ImovelDAO().getTitulo(imovelId);

        // Formata Datas
        iFin.setDtInicioContr(UtilsJSON.cvtUTCDateToBr(iFin.getDtInicioContr()));
        iFin.setDtFimContr(UtilsJSON.cvtUTCDateToBr(iFin.getDtFimContr()));

        model.addAttribute("imovelIdAttr",imovelId);
        model.addAttribute("imovelIdDescr",imovelDescr);
        model.addAttribute("imovelFinanceiro", iFin);
        return "imovelFinanceiro/detail";
    }

    @PostMapping("/imovelFinancUpdate")
    public String imovelFinancUpdate(@ModelAttribute ImovelFinanceiro imovelFinanceiro,
                                  Authentication authentication) {
        //Sysout.s("UPDATE imovelFinanceiro..." + imovelFinanceiro.getImovel_id());
        dao.update(imovelFinanceiro);
        String redirect = "redirect:/imovelList";
        return redirect;
    }

/*    @GetMapping("/imovelPagtoList")
    public String imovelPagtoList(Model model) {
        List<ImovelPagtoListForm> imovelPagtoList = new ArrayList<ImovelPagtoListForm>();
        imovelPagtoList = new ReportsDAO().getImovelPagtosList();
        model.addAttribute("imovelPagtoList",imovelPagtoList);
        return "imovelFinanceiro/pagtoList";
    }*/

    @GetMapping("/imovelPagtoList")
    public String imovelPagtoList(Model model) {
        return "ops";
    }
}
