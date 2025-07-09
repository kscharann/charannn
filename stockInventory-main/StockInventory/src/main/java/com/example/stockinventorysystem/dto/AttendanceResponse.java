package com.example.stockinventorysystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.stockinventorysystem.model.EAttendanceStatus;

public class AttendanceResponse {
    private Long id;
    private String empId;
    private String firstName;
    private String lastName;
    private String role;
    private LocalDate attendanceDate;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private EAttendanceStatus status;
    private String photoBase64;
    private LocalDateTime photoTimestamp;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public EAttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(EAttendanceStatus status) {
        this.status = status;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }

    public LocalDateTime getPhotoTimestamp() {
        return photoTimestamp;
    }

    public void setPhotoTimestamp(LocalDateTime photoTimestamp) {
        this.photoTimestamp = photoTimestamp;
    }
}
