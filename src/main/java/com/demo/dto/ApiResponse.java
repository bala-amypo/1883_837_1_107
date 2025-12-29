package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ClaimRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double weight;
    private double score;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}
