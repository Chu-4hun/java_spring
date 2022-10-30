package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "teacher_info")
public class Teacher_info {
    public Teacher_info(Long id, Date teach_since) {
        this.id = id;
        this.teach_since = teach_since;
    }

    public Teacher_info() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    private Date teach_since;

    @ManyToMany
    @JoinTable(name = "teacher_to_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_info_id"))
    public List<Subject> subjectList;


    @OneToMany(mappedBy = "teacher_info", fetch = FetchType.EAGER)
    private List<User> user_teacher_info;


    //______________________________BOILERPLATE LINE__________________________________


    public List<User> getUser_teacher_info() {
        return user_teacher_info;
    }

    public void setUser_teacher_info(List<User> user_teacher_info) {
        this.user_teacher_info = user_teacher_info;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTeach_since() {
        return teach_since;
    }

    public void setTeach_since(Date teach_since) {
        this.teach_since = teach_since;
    }
}
