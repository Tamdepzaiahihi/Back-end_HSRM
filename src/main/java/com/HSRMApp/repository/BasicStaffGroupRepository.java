package com.HSRMApp.repository;

import com.HSRMApp.entity.BasicStaffGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasicStaffGroupRepository extends JpaRepository<BasicStaffGroup, String> {
    Optional<BasicStaffGroup> findByUserId(Long id);
}
