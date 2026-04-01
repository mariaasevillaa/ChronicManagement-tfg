package com.tfg.wellbeing.model;

public class Achievements {
    private int id;
    private String name;
    private String description;
    private int points_reward;
    private int reports_needed;

    public Achievements(int id, String name, String description, int points_reward,int reports_needed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.points_reward = points_reward;
        this.reports_needed = reports_needed;
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

    public int getPoints_reward() {
        return points_reward;
    }

    public void setPoints_reward(int points_reward) {
        this.points_reward = points_reward;
    }

    public int getReports_needed() {
        return reports_needed;
    }

    public void setReports_needed(int reports_needed) {
        this.reports_needed = reports_needed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Achievements{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", points_reward=" + points_reward +
                ", reports_needed=" + reports_needed +
                '}';
    }
}
