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
    private Long id;

    @PastOrPresent
    private Date submission_date;

    @ManyToOne
    @JoinColumn(name = "target_department_id")
    private Department target_department;

    private Role target_role;

    @ManyToOne
    @JoinColumn(name = "summary_id")
    private Summary summary;

//    @OneToMany(mappedBy = "candidate_info", fetch = FetchType.EAGER)
//    @OneToOne()
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "candidate_info", fetch = FetchType.EAGER)
    private Set<Summary> summaries;

    //______________________________BOILERPLATE LINE__________________________________


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
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

    public Date getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(Date submission_date) {
        this.submission_date = submission_date;
    }

    public Role getTarget_role() {
        return target_role;
    }

    public void setTarget_role(Role target_role) {
        this.target_role = target_role;
    }

    public Set<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Set<Summary> summaries) {
        this.summaries = summaries;
    }
}
