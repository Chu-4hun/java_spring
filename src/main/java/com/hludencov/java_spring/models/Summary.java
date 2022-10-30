package com.hludencov.java_spring.models;


import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private int mark;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    //TODO OneToMany
    @ManyToOne
    @JoinColumn(name = "education_institution_id")
    private Education_institution education_institution;

    //______________________________BOILERPLATE LINE__________________________________

    public Education_institution getEducation_institution() {

        return education_institution;
    }

    public void setEducation_institution(Education_institution education_institution) {
        this.education_institution = education_institution;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
