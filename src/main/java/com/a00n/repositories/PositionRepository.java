package com.a00n.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a00n.entities.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> {

}
