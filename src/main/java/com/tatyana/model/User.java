package com.tatyana.model;

public class User {

    private String login;
    private String password;
    private String fio;
    private String telephone;
    private String email;
    private String role;
    private String status;

    public User(String login, String password, String fio, String telephone, String email, String role, String status) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
