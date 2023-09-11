package com.HSRMApp.repository;

import com.HSRMApp.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface AttendanceRepository extends JpaRepository <Attendance, Long> {

}
