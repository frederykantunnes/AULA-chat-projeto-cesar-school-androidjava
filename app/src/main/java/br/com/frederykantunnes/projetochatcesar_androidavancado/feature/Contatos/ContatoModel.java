package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos;

import java.io.Serializable;

public class ContatoModel implements Serializable {

    private String email, firstName, picture, uid;

    public ContatoModel(String email, String firstName, String picture, String uid) {
        this.email = email;
        this.firstName = firstName;
        this.picture = picture;
        this.uid = uid;
    }

    public ContatoModel(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
