package com.newsportal.newsportal.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "postgroup")
public class PostGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public PostGroup() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }
    /**
     * Setters
     */


    public PostGroup setId(int id) {
        this.id = id;
        return this;
    }

    public PostGroup setName(String name) {
        this.name = name;
        return this;
    }

    public PostGroup setDescription(String description) {
        this.description = description;
        return this;
    }

    public PostGroup setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
        return this;
    }

    public PostGroup setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
        return this;
    }

    public String getTimestampsAsReadable(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

    }
}
