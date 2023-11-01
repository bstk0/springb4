package br.com.dbengine.springb4.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImovelPagtoListForm {
    private Integer imovel_id;
    private String apelido;
    private Integer diaPagtoAluguel;
    private Double vlAluguel;
    private String nomeImobiliaria;

    public ImovelPagtoListForm(Integer imovel_id, String apelido, Integer diaPagtoAluguel,
                               Double vlAluguel,
                               String nomeImobiliaria) {
        this.imovel_id = imovel_id;
        this.apelido = apelido;
        this.diaPagtoAluguel = diaPagtoAluguel;
        this.vlAluguel = vlAluguel;
        this.nomeImobiliaria = nomeImobiliaria;
    }
}
