package br.com.dbengine.springb4.service;

import br.com.dbengine.springb4.Enum.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BancoAplicacaoService {

    // AQUI ESTÁ A MÁGICA: O EnumMap que guarda a relação
    private static final Map<BancoEnum, Set<TipoAplicacaoEnum>> relacaoBancoAplicacao;

    // Bloco estático para inicializar o mapa quando a classe é carregada
    static {
        relacaoBancoAplicacao = new EnumMap<>(BancoEnum.class);

        // Usando as novas constantes de BancoEnum e TipoAplicacaoEnum

        // Bradesco oferece CDB e Previdência
        relacaoBancoAplicacao.put(BancoEnum.MERCADO_PAGO, EnumSet.of(
                TipoAplicacaoEnum.SALDO,
                TipoAplicacaoEnum.CDB
        ));

        // Santander oferece Ações e Fundos Imobiliários
        relacaoBancoAplicacao.put(BancoEnum.SANTANDER, EnumSet.of(
                TipoAplicacaoEnum.SALDO,
                TipoAplicacaoEnum.CDB
        ));

        relacaoBancoAplicacao.put(BancoEnum.PIC_PAY, EnumSet.of(
                TipoAplicacaoEnum.SALDO,
                TipoAplicacaoEnum.CDB
        ));

        // Itaú oferece apenas Previdência
        relacaoBancoAplicacao.put(BancoEnum.ICATU, EnumSet.of(
                TipoAplicacaoEnum.PREVIDENCIA
        ));

        // Banco do Brasil oferece tudo
        //relacaoBancoAplicacao.put(BancoEnum.BANCO_DO_BRASIL, EnumSet.allOf(TipoAplicacaoEnum.class));
    }

    /**
     * Retorna o conjunto de Tipos de Aplicação permitidos para um determinado Banco.
     * @param banco O enum do Banco a ser consultado.
     * @return Um Set com os Tipos de Aplicação permitidos. Retorna um Set vazio se o banco não tiver tipos associados.
     */
    public Set<TipoAplicacaoEnum> getTiposPorBanco(BancoEnum banco) {
        // Retorna os tipos para o banco, ou um conjunto vazio se o banco não estiver no mapa
        return relacaoBancoAplicacao.getOrDefault(banco, EnumSet.noneOf(TipoAplicacaoEnum.class));
    }

    /**
     * Verifica se um determinado Tipo de Aplicação é válido para um Banco.
     * @param banco O enum do Banco.
     * @param tipo O enum do Tipo de Aplicação.
     * @return true se for válido, false caso contrário.
     */
    public boolean isTipoValidoParaBanco(BancoEnum banco, TipoAplicacaoEnum tipo) {
        Set<TipoAplicacaoEnum> tiposPermitidos = getTiposPorBanco(banco);
        return tiposPermitidos.contains(tipo);
    }
}

