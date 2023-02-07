package com.testapi.power;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "power")
public class Power {
    @Id
    private Integer powerId;
    private String name;

    public Power(){

    }
    public Power(Integer powerId, String name) {
        this.powerId = powerId;
        this.name = name;
    }

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
