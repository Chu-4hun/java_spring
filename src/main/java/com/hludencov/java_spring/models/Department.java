package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "target_department", fetch = FetchType.EAGER)
    private Set<Candidate_info> candidate_infos;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<User> department_users;

    //______________________________BOILERPLATE LINE__________________________________


    public Set<Candidate_info> getCandidate_infos() {
        return candidate_infos;
    }

    public void setCandidate_infos(Set<Candidate_info> candidate_infos) {
        this.candidate_infos = candidate_infos;
    }

    public Set<User> getDepartment_users() {
        return department_users;
    }

    public void setDepartment_users(Set<User> department_users) {
        this.department_users = department_users;
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
}
