package br.unigran.firebase.Model;

public class Pessoa {
    private Integer id;
    public String nome;
    public String contato;
    public float avaliacao;

    public Pessoa(){}

    public Pessoa(String nome, String contato, float avaliacao) {
        this.nome = nome;
        this.contato = contato;
        this.avaliacao = avaliacao;
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }
    public String toString(){
        return nome + " " + contato + " " + avaliacao;
    }
}
