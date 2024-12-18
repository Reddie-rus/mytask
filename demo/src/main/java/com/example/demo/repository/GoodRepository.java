package com.example.demo.repository;

import com.example.demo.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Long> {
}