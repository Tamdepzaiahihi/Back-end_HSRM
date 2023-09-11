package com.HSRMApp.repository;

import com.HSRMApp.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository <Position, Long > {
}
