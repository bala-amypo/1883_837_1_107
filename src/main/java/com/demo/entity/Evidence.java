package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;

    @ManyToOne
    private DamageClaim claim;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public DamageClaim getClaim() { return claim; }
    public void setClaim(DamageClaim claim) { this.claim = claim; }
}
