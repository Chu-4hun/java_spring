package com.hludencov.java_spring.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "personal_info")
public class Personal_info {
    public Personal_info(Long id, String name, String surname, @Nullable String sec_name, Date birthdate, int pass_id, boolean is_male) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sec_name = sec_name;
        this.birthdate = birthdate;
        this.pass_id = pass_id;
        this.is_male = is_male;
    }

    public Personal_info() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name, surname;

    @Nullable
    private String sec_name;

    @PastOrPresent
    private Date birthdate;

    @NotNull
    private int pass_id;
    @NotNull
    private boolean is_male;

    @OneToMany(mappedBy = "personal_info", fetch = FetchType.EAGER)
    private Set<User> user_personal_info;

    //______________________________BOILERPLATE LINE__________________________________


    public Set<User> getUser_personal_info() {
        return user_personal_info;
    }

    public void setUser_personal_info(Set<User> user_personal_info) {
        this.user_personal_info = user_personal_info;
    }

    public int getPass_id() {
        return pass_id;
    }

    public void setPass_id(int pass_id) {
        this.pass_id = pass_id;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Nullable
    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(@Nullable String sec_name) {
        this.sec_name = sec_name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isIs_male() {
        return is_male;
    }

    public void setIs_male(boolean is_male) {
        this.is_male = is_male;
    }
}
