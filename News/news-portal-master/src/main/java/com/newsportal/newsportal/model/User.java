package com.newsportal.newsportal.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private String phoneNumber;
    private String address;
    private boolean beInUse;
    @ManyToOne
    private Group group;

    @ManyToMany
    private List<PostGroup> postGroup;


    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public boolean isBeInUse() {
        return beInUse;
    }

    public Group getGroup() {
        return group;
    }

    public List<PostGroup> getPostGroup() {
        return postGroup;
    }

    /**
     * Setters
     *
     */
    public User setId(int id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setBeInUse(boolean beInUse) {
        this.beInUse = beInUse;
        return this;
    }

    public User setGroup(Group group) {
        this.group = group;
        return this;
    }

    public User setPostGroup(List<PostGroup> postGroup) {
        this.postGroup = postGroup;
        return this;
    }
}
