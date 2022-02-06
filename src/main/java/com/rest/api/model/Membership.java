package com.rest.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Membership {

    @Id
    @Column(name = "type")
    private String type;
    @Column(name = "max_price")
    private Integer maxPrice;
    @Column(name = "min_price")
    private Integer minPrice;
    @Column(name = "tax")
    private Float tax;
    @Column(name = "max_price_gst")
    private Float maxGstPrice;
    @Column(name = "min_price_gst")
    private Float minGstPrice;
    @Column(name = "remarks")
    private String remarks;


//    @OneToMany(mappedBy = "membership", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Client_Membership> client_membership;


//    public Set<Client_Membership> getClient_membership() {
//        return client_membership;
//    }
//
//
//    public void setClient_membership(Set<Client_Membership> client_membership) {
//        this.client_membership = client_membership;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Float getMaxGstPrice() {
        return maxGstPrice;
    }

    public void setMaxGstPrice(Float maxGstPrice) {
        this.maxGstPrice = maxGstPrice;
    }

    public Float getMinGstPrice() {
        return minGstPrice;
    }

    public void setMinGstPrice(Float minGstPrice) {
        this.minGstPrice = minGstPrice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
