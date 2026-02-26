package com.tfg.wellbeing.model;

public class Achievements {
    private int id;
    private String name;
    private int points_reward;

    public Achievements(int id, String name, int points_reward) {
        this.id = id;
        this.name = name;
        this.points_reward = points_reward;
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

    @Override
    public String toString() {
        return "Achievements{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", points_reward=" + points_reward +
                '}';
    }
}
