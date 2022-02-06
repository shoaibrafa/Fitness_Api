package com.rest.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.Set;

@Entity
public class Client {

    @Id
    @Column(name = "MID")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "lname")
    private String lastName;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "email")
    private String email;
    @Column(name = "joining_date")
    private Date joiningDate;
    @Column(name = "status")
    private String status;
    @Column(name = "remarks")
    private String remarks;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<Client_Membership> client_membership;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<PersonalTraining> personalTrainings;


    //Setter and Getter Methods

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<Client_Membership> getClient_membership() {
        return client_membership;
    }

    public void setClient_membership(Set<Client_Membership> client_membership) {
        this.client_membership = client_membership;
    }
}
