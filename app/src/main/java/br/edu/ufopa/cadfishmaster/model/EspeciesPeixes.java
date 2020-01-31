package br.edu.ufopa.cadfishmaster.model;

public class EspeciesPeixes {

    String nome;
    byte[] imagem;

    public EspeciesPeixes() {
    }

    public EspeciesPeixes(String nome, byte[] imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
