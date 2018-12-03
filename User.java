package com.cse327.pothik;

import java.io.Serializable;

/**
 * Created by Sarker Rabi on 16-11-17.
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dispalyName;
    private String email;
    private String url;

    public User(String dispalyName, String email, String url) {
        this.dispalyName = dispalyName;
        this.email = email;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDispalyName() {
        return dispalyName;
    }

    public void setDispalyName(String dispalyName) {
        this.dispalyName = dispalyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
