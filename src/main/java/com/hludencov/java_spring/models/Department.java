package com.hludencov.java_spring.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Department")
public class Department {
    public Department(Long id, String name, List<Candidate_info> candidate_infos, List<User> department_users) {
        this.id = id;
        this.name = name;
        this.candidate_infos = candidate_infos;
        this.department_users = department_users;
    }

    public Department() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "target_department", fetch = FetchType.EAGER)
    private List<Candidate_info> candidate_infos;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<User> department_users;

    //______________________________BOILERPLATE LINE__________________________________


    public List<User> getDepartment_users() {
        return department_users;
    }

    public void setDepartment_users(List<User> department_users) {
        this.department_users = department_users;
    }

    public List<Candidate_info> getCandidate_infos() {
        return candidate_infos;
    }

    public void setCandidate_infos(List<Candidate_info> candidate_infos) {
        this.candidate_infos = candidate_infos;
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
