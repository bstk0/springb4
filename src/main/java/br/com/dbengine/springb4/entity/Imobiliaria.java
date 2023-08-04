package br.com.dbengine.springb4.entity;

public class Imobiliaria {
    private Integer id;
    private String nome;
    private String referencia;
    private String telPrincipal;
    private String contato1;
    private String contato2;
    private String contato3;
    private String tel1;
    private String tel2;
    private String tel3;

    public Imobiliaria(Integer id, String nome, String referencia, String telPrincipal,
                       String contato1, String contato2, String contato3,
                       String tel1, String tel2, String tel3) {
        this.id = id;
        this.nome = nome;
        this.referencia = referencia;
        this.telPrincipal = telPrincipal;
        this.contato1 = contato1;
        this.contato2 = contato2;
        this.contato3 = contato3;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTelPrincipal() {
        return telPrincipal;
    }

    public void setTelPrincipal(String telPrincipal) {
        this.telPrincipal = telPrincipal;
    }

    public String getContato1() {
        return contato1;
    }

    public void setContato1(String contato1) {
        this.contato1 = contato1;
    }

    public String getContato2() {
        return contato2;
    }

    public void setContato2(String contato2) {
        this.contato2 = contato2;
    }

    public String getContato3() {
        return contato3;
    }

    public void setContato3(String contato3) {
        this.contato3 = contato3;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }
}
