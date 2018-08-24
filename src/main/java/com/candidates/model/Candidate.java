package com.candidates.model;

import java.util.Arrays;

public class Candidate {

    private String name;

    private String interests[];

    private String countryOrigin;

    private String countryLiving;

    private String city;

    private String position;

    private double salary;

    public Candidate(CandidateRequest candidateRequest) {
        this.name = candidateRequest.getName();
        setInterests(candidateRequest.getInterests());
        this.countryLiving = candidateRequest.getCountryLiving();
        this.countryLiving = candidateRequest.getCountryLiving();
        this.city = candidateRequest.getCity();
        this.position = candidateRequest.getPosition();
        this.salary = candidateRequest.getSalary();
    }

    public Candidate() {

    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Dev{" +
                "name='" + name + '\'' +
                ", interests=" + Arrays.toString(interests) +
                ", countryOrigin='" + countryOrigin + '\'' +
                ", countryLiving='" + countryLiving + '\'' +
                ", city='" + city + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

}
