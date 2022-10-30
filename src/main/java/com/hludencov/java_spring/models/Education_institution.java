package com.hludencov.java_spring.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Education_institution {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String institution_name, address;

    @OneToMany(mappedBy = "education_institution", fetch = FetchType.EAGER)
    private Set<Summary> summaries;

    //______________________________BOILERPLATE LINE__________________________________

    public Set<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Set<Summary> summaries) {
        this.summaries = summaries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String institution_address) {
        this.address = institution_address;
    }
}
