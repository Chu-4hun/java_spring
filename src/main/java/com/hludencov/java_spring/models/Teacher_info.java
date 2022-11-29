package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "teacher_info")
public class Teacher_info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    public Date teachSince;

    @ManyToMany
    @JoinTable(name = "teacher_to_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_info_id"))
    public Set<Subject> subjectList;


    @OneToMany(mappedBy = "teacher_info", fetch = FetchType.EAGER)
    public Set<User> user_teacher_info;


    //______________________________BOILERPLATE LINE__________________________________


    public Set<User> getUser_teacher_info() {
        return user_teacher_info;
    }

    public void setUser_teacher_info(Set<User> user_teacher_info) {
        this.user_teacher_info = user_teacher_info;
    }

    public Set<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Set<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTeachSince() {
        return teachSince;
    }

    public void setTeachSince(Date teachSince) {
        this.teachSince = teachSince;
    }
}
