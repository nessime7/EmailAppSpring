package com.spring.EmailAppSpring.model;

import java.util.Objects;

public class Email {

    private String login;
    private String password;

    public Email(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Email() {
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

    @Override
    public String toString() {
        return "Email{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(login, email.login) && Objects.equals(password, email.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

}
