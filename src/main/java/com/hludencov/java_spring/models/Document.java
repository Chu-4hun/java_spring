package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Document {

    public Document(Long id, User user, String file_name, Date date, Date archive_date) {
        this.id = id;
        this.user = user;
        this.file_name = file_name;
        this.date = date;
        this.archive_date = archive_date;
    }

    public Document() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private String file_name;

    @PastOrPresent
    private Date date;

    @FutureOrPresent
    private Date archive_date;


    @OneToMany(mappedBy = "document", fetch = FetchType.EAGER)
    private List<Summary> summaries;


    //______________________________BOILERPLATE LINE__________________________________


    public List<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<Summary> summaries) {
        this.summaries = summaries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getArchive_date() {
        return archive_date;
    }

    public void setArchive_date(Date archive_date) {
        this.archive_date = archive_date;
    }
}
