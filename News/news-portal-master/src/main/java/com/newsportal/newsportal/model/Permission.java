package com.newsportal.newsportal.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @ManyToMany
    private List<Group> groups;

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Permission() {
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

    public List<Group> getGroups() {
        return groups;
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

    public Permission setId(int id) {
        this.id = id;
        return this;
    }

    public Permission setName(String name) {
        this.name = name;
        return this;
    }

    public Permission setDescription(String description) {
        this.description = description;
        return this;
    }

    public Permission setGroups(List<Group> groups) {
        this.groups = groups;
        return this;
    }

    public Permission setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
        return this;
    }

    public Permission setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
        return this;
    }
}
