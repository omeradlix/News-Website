package com.newsportal.newsportal.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    @OneToOne
    private User user;

    @ManyToOne
    private SecurityQuestion securityQuestion;

    private String securityQuestionAnswer;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public UserAccount() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User getUser() {
        return user;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public UserAccount setId(int id) {
        this.id = id;
        return this;
    }

    public UserAccount setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserAccount setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserAccount setUser(User user) {
        this.user = user;
        return this;
    }

    public UserAccount setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
        return this;
    }

    public UserAccount setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
        return this;
    }

    public UserAccount setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
        return this;
    }

    public UserAccount setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
        return this;
    }
}
