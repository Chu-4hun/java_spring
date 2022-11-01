package com.hludencov.java_spring.models;


import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;

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

    @ManyToOne
    @JoinColumn(name = "education_institution_id")
    private Education_institution education_institution;
    @ManyToOne
    @JoinColumn(name = "candidate_info_id")
    private Candidate_info candidate_info;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Candidate_info getCandidate_info() {
        return candidate_info;
    }

    public void setCandidate_info(Candidate_info candidate_info) {
        this.candidate_info = candidate_info;
    }
}
