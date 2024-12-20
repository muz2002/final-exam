package com.example.entity;


import jakarta.persistence.*;

@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String name;
    private String description;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
}
