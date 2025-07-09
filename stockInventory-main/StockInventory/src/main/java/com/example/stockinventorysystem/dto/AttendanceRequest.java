package com.example.stockinventorysystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.stockinventorysystem.model.EAttendanceStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AttendanceRequest {
    @NotBlank
    private String empId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String role;

    @NotNull
    private LocalDate attendanceDate;

    @NotNull
    private LocalTime timeIn;

    @NotNull
    private LocalTime timeOut;

    @NotNull
    private EAttendanceStatus status;

    // Getters and Setters
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
}
