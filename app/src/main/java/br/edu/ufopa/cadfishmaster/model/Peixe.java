package br.edu.ufopa.cadfishmaster.model;

public class Peixe {

    private String especie;
    private double peso;
    private double tamanho;
    private String marca_tag;
    private String posicao;

    public Peixe(){

    }

    public Peixe(String especie, double peso, double tamanho, String marca_tag, String posicao) {
        this.especie = especie;
        this.peso = peso;
        this.tamanho = tamanho;
        this.marca_tag = marca_tag;
        this.posicao = posicao;
    }

    public String getNome() {
        return especie;
    }

    public void setNome(String nome) {
        this.especie = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public String getMarca_tag() {
        return marca_tag;
    }

    public void setMarca_tag(String marca_tag) {
        this.marca_tag = marca_tag;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
}
