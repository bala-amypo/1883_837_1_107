package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidence")
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DamageClaim claim;

    private String evidenceType;
    private String fileUrl;
    private LocalDateTime uploadedAt;

    @PrePersist
    void onCreate() {
        this.uploadedAt = LocalDateTime.now();
    }

    public Evidence() {}
}
