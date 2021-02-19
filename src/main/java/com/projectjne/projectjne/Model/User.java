package com.projectjne.projectjne.Model;

import javax.validation.constraints.NotNull;

public class User {
    @NotNull(message = "id user may not be null")
    private String idUser;
    @NotNull(message = "username may not be null")
    private String username;
    @NotNull(message = "email may not be null")
    private String email;
    @NotNull(message = "password may not be null")
    private String password;

    public User(String idUser, String username, String email, String password) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
