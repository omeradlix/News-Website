package com.newsportal.newsportal.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    private boolean verified;

    private boolean privacy;

    @ManyToOne
    private User author;

    @ManyToOne
    private PostGroup postGroup;

    @CreationTimestamp
    private LocalDateTime created_at;



    public Post() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isVerified() {
        return verified;
    }

    public boolean isPrivacy(){ return privacy; }

    public User getAuthor() {
        return author;
    }

    public PostGroup getPostGroup() {
        return postGroup;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }


    /**
     * Setters
     */

    public Post setId(int id) {
        this.id = id;
        return this;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public Post setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Post setVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    public Post setPrivacy(boolean privacy) {
        this.privacy = privacy;
        return this;
    }

    public Post setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Post setPostGroup(PostGroup postGroup) {
        this.postGroup = postGroup;
        return this;
    }

    public Post setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
        return this;
    }


    public String getLocalDateTimeAsReadable(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public String getAuthorAndLocalDateTimeAsReadable(LocalDateTime localDateTime){
        return this.author.getName() + " " + this.author.getLastname()  + " | " + localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    public String getAuthorNameAndLastname(){
        return this.author.getName() + " " + this.author.getLastname();
    }
}
