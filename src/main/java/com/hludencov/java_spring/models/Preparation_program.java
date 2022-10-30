package com.hludencov.java_spring.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Entity
@Table(name = "preparation_program")
public class Preparation_program {
    public Preparation_program(Long id, String name, List<Subject> subjectList) {
        this.id = id;
        this.name = name;
        this.subjectList = subjectList;
    }

    public Preparation_program() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    @JoinTable(name = "prep_to_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "preparation_program_id"))
    public List<Subject> subjectList;

    @OneToMany(mappedBy = "preparation_program", fetch = FetchType.EAGER)
    private List<Group> groups;


    //______________________________BOILERPLATE LINE__________________________________


    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
}
