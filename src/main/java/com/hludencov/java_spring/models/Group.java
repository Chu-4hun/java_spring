package com.hludencov.java_spring.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Group {
    public Group(Long id, List<User> users, User teacher_organizer, Preparation_program preparation_program) {
        this.id = id;
        this.users = users;
        this.teacher_organizer = teacher_organizer;
        this.preparation_program = preparation_program;
    }

    public Group() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany
    @JoinTable(name = "user_to_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
    @ManyToOne
    @JoinColumn(name = "teacher_organizer_id")
    private User teacher_organizer;

    @ManyToOne
    @JoinColumn(name = "preparation_program_id")
    private Preparation_program preparation_program;


    //______________________________BOILERPLATE LINE__________________________________


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Preparation_program getPreparation_program() {
        return preparation_program;
    }

    public void setPreparation_program(Preparation_program preparation_program) {
        this.preparation_program = preparation_program;
    }

    public User getTeacher_organizer() {
        return teacher_organizer;
    }

    public void setTeacher_organizer(User teacher_organizer) {
        this.teacher_organizer = teacher_organizer;
    }
}
