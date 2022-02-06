package com.rest.api.model;


import java.sql.Date;

import javax.persistence.*;

@Entity
public class Client_Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //here client_id is the name of the column in client_membership table referencing the MID column in client table
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    //here m_id is the column in client_membership table referencing the type column in the membership table
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "m_id", nullable = false)
    private Membership membership;

    @Column(name = "start_date")
    private Date start_date;
    @Column(name = "end_date")
    private Date end_date;
    @Column(name = "payment")
    private Integer payment;
    @Column(name = "balance")
    private Integer balance;
    @Column(name = "payment_date")
    private Date payment_date;
    @Column(name = "payment_method")
    private String payment_method;
    @Column(name = "remarks")
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
