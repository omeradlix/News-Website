package com.newsportal.newsportal.model;


import javax.persistence.*;

@Entity
public class SecurityQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;

    public SecurityQuestion() {
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public SecurityQuestion setId(int id) {
        this.id = id;
        return this;
    }

    public SecurityQuestion setQuestion(String question) {
        this.question = question;
        return this;
    }
}
