package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    public User user;

    public String fileName;

    @PastOrPresent
    public Date date;

    @FutureOrPresent
    public Date archive_date;

    public boolean isToAdmission;


    @OneToMany(mappedBy = "document", fetch = FetchType.EAGER)
    private Set<Summary> summaries;


    //______________________________BOILERPLATE LINE__________________________________


    public boolean isToAdmission() {
        return isToAdmission;
    }

    public void setToAdmission(boolean toAdmission) {
        isToAdmission = toAdmission;
    }

    public Set<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Set<Summary> summaries) {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
