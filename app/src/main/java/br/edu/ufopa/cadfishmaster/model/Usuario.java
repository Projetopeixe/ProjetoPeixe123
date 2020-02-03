package br.edu.ufopa.cadfishmaster.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private byte[] icon;

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, byte[] icon) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.icon = icon;
    }

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


}
