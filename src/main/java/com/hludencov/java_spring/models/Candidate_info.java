package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "candidate_info")
public class Candidate_info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @PastOrPresent
    public Date submissionDate;

    @ManyToOne
    @JoinColumn(name = "target_department_id")
    public Department target_department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;

    @OneToMany(mappedBy = "candidate_info", fetch = FetchType.EAGER)
    public Set<Summary> summaries;

    //______________________________BOILERPLATE LINE__________________________________


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Department getTarget_department() {
        return target_department;
    }

    public void setTarget_department(Department target_department) {
        this.target_department = target_department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submission_date) {
        this.submissionDate = submission_date;
    }


    public Set<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Set<Summary> summaries) {
        this.summaries = summaries;
    }
}
