package com.hludencov.java_spring.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    public Subject(Long id, String subject_code, int subject_name, List<Teacher_info> teacher_infoList) {
        this.id = id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.teacher_infoList = teacher_infoList;
    }

    public Subject() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject_code;
    private int subject_name;

    @ManyToMany
    @JoinTable(name = "teacher_to_subject",
            joinColumns = @JoinColumn(name = "teacher_info_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    public List<Teacher_info> teacher_infoList;

    @ManyToMany
    @JoinTable(name = "prep_to_subject",
            joinColumns = @JoinColumn(name = "preparation_program_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    public List<Preparation_program> preparation_programs;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<Summary> summaries;


    //______________________________BOILERPLATE LINE__________________________________

    public List<Preparation_program> getPreparation_programs() {
        return preparation_programs;
    }

    public void setPreparation_programs(List<Preparation_program> preparation_programs) {
        this.preparation_programs = preparation_programs;
    }

    public List<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<Summary> summaries) {
        this.summaries = summaries;
    }

    public List<Teacher_info> getTeacher_infoList() {
        return teacher_infoList;
    }

    public void setTeacher_infoList(List<Teacher_info> teacher_infoList) {
        this.teacher_infoList = teacher_infoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public int getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(int subject_name) {
        this.subject_name = subject_name;
    }
}
