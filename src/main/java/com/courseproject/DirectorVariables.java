package com.courseproject;

public class DirectorVariables extends User {
    private int id;
    private String name;
    private int salary;
    private String role;
    private String pos;
    private int bonus;

    public DirectorVariables(int id, String name, int salary, String role, String pos, int bonus) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.role = role;
        this.pos = pos;
        this.bonus = bonus;
    }

    public DirectorVariables() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}

