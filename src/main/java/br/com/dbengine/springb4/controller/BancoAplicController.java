package br.com.dbengine.springb4.controller;

import br.com.dbengine.springb4.Enum.*;
import br.com.dbengine.springb4.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class BancoAplicController {
    @Autowired
    private BancoAplicacaoService bancoAplicacaoService;

    // Endpoint para carregar a página principal
    @GetMapping("/combos")
    public String paginaDeCombos(Model model) {
        // Pega a lista de todos os bancos para popular o primeiro combo
        List<BancoEnum> todosOsBancos = Arrays.asList(BancoEnum.values());
        model.addAttribute("bancos", todosOsBancos);

        // Opcional: já carrega os tipos para o primeiro banco da lista
        if (!todosOsBancos.isEmpty()) {
            BancoEnum primeiroBanco = todosOsBancos.get(0);
            model.addAttribute("tiposAplicacao", bancoAplicacaoService.getTiposPorBanco(primeiroBanco));
        } else {
            model.addAttribute("tiposAplicacao", Collections.emptySet());
        }

        return "pagina-combos"; // Nome do arquivo HTML (ex: pagina-combos.html)
    }

    // --- ENDPOINT PARA A CHAMADA AJAX ---
    // Este endpoint retorna apenas os dados (JSON), não uma página inteira
    @GetMapping("/api/tipos-por-banco")
    @ResponseBody // MUITO IMPORTANTE: Diz ao Spring para retornar os dados diretamente, não renderizar um template
    public Set<TipoAplicacaoEnum> getTiposPorBanco(@RequestParam("banco") BancoEnum banco) {
        // Recebe o nome do banco como parâmetro da URL (ex: /api/tipos-por-banco?banco=BANCO_B)
        return bancoAplicacaoService.getTiposPorBanco(banco);
    }

}
