package com.hludencov.java_spring.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;


@Entity
@Table(name = "preparation_program")
public class Preparation_program {
    public Preparation_program(Long id, String name, Set<Subject> subjectSet) {
        this.id = id;
        this.name = name;
        this.subjectSet = subjectSet;
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
    public Set<Subject> subjectSet;

    @OneToMany(mappedBy = "preparation_program", fetch = FetchType.EAGER)
    private Set<Group> groups;


    //______________________________BOILERPLATE LINE__________________________________


    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
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

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }
}
