package com.hludencov.java_spring.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany
    @JoinTable(name = "user_to_group",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;
    @ManyToOne
    @JoinColumn(name = "user_id")
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
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
