package com.hludencov.java_spring.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
     public User(String nickname, String birthdate, int views,int friends) {
        this.nickname = nickname;
        this.birthdate = birthdate;
        this.views = views;
        this.friends = friends;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nickname, birthdate;
    private int views, friends;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public String getNickname() {
        return nickname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public int getViews() {
        return views;
    }

    public int getFriends() {
        return friends;
    }

    public Long getId() {
        return id;
    }
}
