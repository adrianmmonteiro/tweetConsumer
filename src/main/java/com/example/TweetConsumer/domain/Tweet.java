package com.example.TweetConsumer.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tweet {

    @Id
    private String id;
    private String usuario;
    private String texto;
    private String localization;
    private boolean validation;
    @ElementCollection(targetClass=String.class)
    private List<String> hashtags;

    public Tweet() {
    }

    public Tweet(String id, String usuario, String texto, String localization, boolean validation, List<String> hashtags) {
        this.id = id;
        this.usuario = usuario;
        this.texto = texto;
        this.localization = localization;
        this.validation = validation;
        this.hashtags = hashtags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
}
