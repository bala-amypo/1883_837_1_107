package com.example.demo.repository;

import com.example.demo.entity.ClaimRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRuleRepository extends JpaRepository<ClaimRule, Long> {

    List<ClaimRule> findAll();
}
