package com.example.demo.repository;

import com.example.demo.entity.DamageClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DamageClaimRepository extends JpaRepository<DamageClaim, Long> {

    List<DamageClaim> findByParcel_Id(Long parcelId);
}
