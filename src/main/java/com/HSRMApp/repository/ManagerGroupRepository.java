package com.HSRMApp.repository;

import com.HSRMApp.entity.ManagerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerGroupRepository extends JpaRepository<ManagerGroup, String> {
    Optional<ManagerGroup> findByUserId(Long id);
}
