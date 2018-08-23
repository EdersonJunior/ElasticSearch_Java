package com.candidates.model;

public class CandidateRequest {

    private String name;

    private String interests[];

    private String countryOrigin;

    private String countryLiving;

    private String city;

    private String position;

    private double salary;

    private int id;

    public CandidateRequest(Candidate candidate) {
        this.name = candidate.getName();
        setInterests(candidate.getInterests());
        this.countryLiving = candidate.getCountryLiving();
        this.countryLiving = candidate.getCountryLiving();
        this.city = candidate.getCity();
        this.position = candidate.getPosition();
        this.salary = candidate.getSalary();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
