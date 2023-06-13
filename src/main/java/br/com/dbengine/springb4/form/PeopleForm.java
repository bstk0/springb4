package br.com.dbengine.springb4.form;

public class PeopleForm {
    // html+= '<tr><td></td><td></td><td></td><</td></tr>';
    private String codigoInterno;
    private String Nome;
    private String DataNascimento;
    private String Observacao;

    public PeopleForm(String codigoInterno, String nome, String dataNascimento, String observacao) {
        this.codigoInterno = codigoInterno;
        Nome = nome;
        DataNascimento = dataNascimento;
        Observacao = observacao;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String observacao) {
        Observacao = observacao;
    }
}
