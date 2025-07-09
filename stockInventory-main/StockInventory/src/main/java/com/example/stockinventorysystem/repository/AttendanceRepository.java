package com.example.stockinventorysystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stockinventorysystem.model.Attendance;
import com.example.stockinventorysystem.model.EAttendanceStatus;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmpId(String empId);
    List<Attendance> findByStatus(EAttendanceStatus status);
    List<Attendance> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByEmpIdAndAttendanceDate(String empId, LocalDate attendanceDate);
}
