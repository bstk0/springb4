package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.DAO.ImovelDAO;
import br.com.dbengine.springb4.DAO.ImovelOcorrenciaDAO;
import br.com.dbengine.springb4.dbUtil.Sysout;
import br.com.dbengine.springb4.entity.ImovelOcorrencia;
import br.com.dbengine.springb4.form.ImovelOcorrForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ImovelOcorrenciaController {

    private final ImovelOcorrenciaDAO dao = new ImovelOcorrenciaDAO();

    @GetMapping("/imovelOcorrenciaList")
    public String imovelOcorrenciaList(Model model, @RequestParam String imovelId) {
        List<ImovelOcorrForm> iOccListForm = dao.getListForm(imovelId);
        // Descriçáo do Imovel
        String imovelDescr = new ImovelDAO().getItem(imovelId).getDescricao();

        model.addAttribute("imovelIdAttr",imovelId);
        model.addAttribute("imovelIdDescr",imovelDescr);
        model.addAttribute("imovelOccList",iOccListForm);
        return "imovelOcorrencia/list";
    }

    @GetMapping("/imovelOcorrenciaAdd")
    public String imovelOcorrenciaAdd(Model model, @RequestParam Integer imovelId) {
        ImovelOcorrencia imovOccAdd = new ImovelOcorrencia();
        imovOccAdd.setImovel_id(imovelId);
        model.addAttribute("imovelIdAttr",imovelId);
        model.addAttribute("imovelOcorrencia",imovOccAdd);
        return "imovelOcorrencia/add";
    }

    //@GetMapping("/imovelOcorrenciaSave")
    @PostMapping("/imovelOcorrenciaSave")
    public String imovelOcorrenciaSave(@ModelAttribute ImovelOcorrencia imovelOcorrencia,
                                       Authentication authentication
                                       )  {
        imovelOcorrencia.setCreatedBy(authentication.getName());
        dao.add(imovelOcorrencia);
        String s = "redirect:/imovelOcorrenciaList?imovelId=" + imovelOcorrencia.getImovel_id();
        return s;
    }

    @GetMapping("/imovelOccUpdForm")
    public String imovelOccUpdForm(@RequestParam String imovelOccId,
                                   Model model) {
        Sysout.s("imovelOccUpdForm...");
        ImovelOcorrForm imovelOccUpd = new ImovelOcorrForm();
        imovelOccUpd = dao.getItemForm(imovelOccId);
        model.addAttribute("imovelOcorrencia", imovelOccUpd);
        return "imovelOcorrencia/update";
    }

    @PostMapping("/imovelOcorrenciaUpdate")
    public String imovelOccUpdate(@ModelAttribute ImovelOcorrencia imovelOcorrencia,
                                  Authentication authentication) {
        Sysout.s("UPDATE imovelOcorrencia..." + imovelOcorrencia.getId());
        imovelOcorrencia.setUpdatedBy(authentication.getName());
        dao.update(imovelOcorrencia);
        String redirect = "redirect:/imovelOcorrenciaList?imovelId=" + imovelOcorrencia.getImovel_id();
        return redirect;
    }

    @GetMapping("/imovelOcorrenciaDelete")
    public String imovelOccDelete(@RequestParam String imovelOccId,
                                  @RequestParam String imovelId) {
        dao.delete(imovelOccId);
        String redirect = "redirect:/imovelOcorrenciaList?imovelId=" + imovelId;
        return redirect;
    }
}
