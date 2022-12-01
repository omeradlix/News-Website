package com.newsportal.newsportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "usergroup")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private boolean beInUse;

    public Group() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isBeInUse() {
        return beInUse;
    }

    public Group setId(int id) {
        this.id = id;
        return this;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public Group setBeInUse(boolean beInUse) {
        this.beInUse = beInUse;
        return this;
    }
}
