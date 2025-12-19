package br.com.dbengine.springb4.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.*;

import java.util.List;

//@Data
//@Getter
//@Setter
public class SaldosApiResponseDTO {
    // Mapeia o objeto raiz "Saldos" do JSON
    @JsonProperty("Saldos")
    private List<SaldoHasuraDTO> saldos;

    // --- MÉTODO GETTER ADICIONADO MANUALMENTE ---
    // Este é o método que o compilador está procurando e não encontrava.
    public List<SaldoHasuraDTO> getSaldos() {
        return this.saldos;
    }

    // (Opcional) Adicione o setter também, por boas práticas.
    public void setSaldos(List<SaldoHasuraDTO> saldos) {
        this.saldos = saldos;
    }
}
