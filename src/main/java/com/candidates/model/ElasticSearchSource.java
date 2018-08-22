package com.candidates.model;

import java.io.Serializable;

public class ElasticSearchSource implements Serializable {

    private String name;
    private String interests[];
    private String city;
    private String countryOrigin;
    private String countryLiving;
    private String position;
    private Double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getCountryLiving() {
        return countryLiving;
    }

    public void setCountryLiving(String countryLiving) {
        this.countryLiving = countryLiving;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
