package com.hludencov.java_spring.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

    public Movie(String name,String release_date,double rating,int series_amount, int comments_amount){
        this.name=name;
        this.release_date=release_date;
        this.rating=rating;
        this.series_amount=series_amount;
        this.comments_amount=comments_amount;
    }

    public Movie(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;




    private String name, release_date;
    private double rating;
    private int series_amount, comments_amount;

    public int getSeries_amount() {
        return series_amount;
    }

    public void setSeries_amount(int series_amount) {
        this.series_amount = series_amount;
    }

    public int getComments_amount() {
        return comments_amount;
    }

    public void setComments_amount(int comments_amount) {
        this.comments_amount = comments_amount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}

