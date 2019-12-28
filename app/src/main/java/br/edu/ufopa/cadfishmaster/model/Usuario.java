package br.edu.ufopa.cadfishmaster.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private byte[] imagem;
    private Bitmap avatar;
    private String urlAvatar;


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;
            }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
        if(this.imagem != null){
            this.avatar = BitmapFactory.decodeByteArray(this.imagem, 0, this.imagem.length);
        }
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }
}
