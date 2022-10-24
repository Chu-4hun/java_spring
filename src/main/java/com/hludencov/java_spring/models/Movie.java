package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table (name = "movies")
public class Movie {

    public Movie(String name,Date release_date,double rating,int series_amount, int comments_amount){
        this.name=name;
        this.release_date=release_date;
        this.rating=rating;
        this.series_amount=series_amount;
        this.comments_amount=comments_amount;
    }

    public Movie(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull(message = "ыыыыедите корректную дату")
    private Date release_date;

    @NotNull
    @PositiveOrZero
    private double rating;
    @NotNull
    @PositiveOrZero
    private int series_amount, comments_amount;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private List<Post> post;

    @ManyToMany()
    @JoinTable(name = "genres_movie",
            joinColumns = @JoinColumn (name = "movie_id"),
            inverseJoinColumns = @JoinColumn (name = "genre_id")
    )
    private List<Post> genres;

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

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

