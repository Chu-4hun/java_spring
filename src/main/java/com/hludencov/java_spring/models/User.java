package com.hludencov.java_spring.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    public User(String nickname, Date birthdate, Date register_date, int views, int friends) {
        this.nickname = nickname;
        this.birthdate = birthdate;
        this.views = views;
        this.friends = friends;
        this.register_date = register_date;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String nickname;
    @NotNull
    @Past
    private Date birthdate;
    @NotNull
    @PastOrPresent
    private Date register_date;
    @NotNull
    @PositiveOrZero
    private int views, friends;

    @OneToOne(mappedBy = "user")
    private Post post;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBirthdate(Date birthdate) {
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

    public Date getBirthdate() {
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

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }
}
