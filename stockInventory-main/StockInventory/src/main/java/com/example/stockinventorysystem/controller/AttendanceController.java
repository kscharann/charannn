package com.example.stockinventorysystem.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.stockinventorysystem.dto.AttendanceRequest;
import com.example.stockinventorysystem.dto.AttendanceResponse;
import com.example.stockinventorysystem.dto.MessageResponse;
import com.example.stockinventorysystem.model.Attendance;
import com.example.stockinventorysystem.model.EAttendanceStatus;
import com.example.stockinventorysystem.repository.AttendanceRepository;
import com.example.stockinventorysystem.service.PhotoStorageService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<AttendanceResponse>> getAllAttendance() {
        List<Attendance> attendance = attendanceRepository.findAll();
        List<AttendanceResponse> responses = attendance.stream()
                .map(this::convertToAttendanceResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAttendanceById(@PathVariable Long id) {
        return attendanceRepository.findById(id)
                .map(attendance -> ResponseEntity.ok(convertToAttendanceResponse(attendance)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceByEmployee(@PathVariable String empId) {
        List<Attendance> attendance = attendanceRepository.findByEmpId(empId);
        List<AttendanceResponse> responses = attendance.stream()
                .map(this::convertToAttendanceResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceByStatus(@PathVariable EAttendanceStatus status) {
        List<Attendance> attendance = attendanceRepository.findByStatus(status);
        List<AttendanceResponse> responses = attendance.stream()
                .map(this::convertToAttendanceResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/date-range")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Attendance> attendance = attendanceRepository.findByAttendanceDateBetween(startDate, endDate);
        List<AttendanceResponse> responses = attendance.stream()
                .map(this::convertToAttendanceResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createAttendance(@Valid @RequestBody AttendanceRequest attendanceRequest) {
        if (attendanceRepository.existsByEmpIdAndAttendanceDate(
                attendanceRequest.getEmpId(), attendanceRequest.getAttendanceDate())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Attendance already exists for this employee on this date!"));
        }

        Attendance attendance = new Attendance();
        attendance.setEmpId(attendanceRequest.getEmpId());
        attendance.setFirstName(attendanceRequest.getFirstName());
        attendance.setLastName(attendanceRequest.getLastName());
        attendance.setRole(attendanceRequest.getRole());
        attendance.setAttendanceDate(attendanceRequest.getAttendanceDate());
        attendance.setTimeIn(attendanceRequest.getTimeIn());
        attendance.setTimeOut(attendanceRequest.getTimeOut());
        attendance.setStatus(attendanceRequest.getStatus());

        attendanceRepository.save(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToAttendanceResponse(attendance));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateAttendance(@PathVariable Long id,
                                            @Valid @RequestBody AttendanceRequest attendanceRequest) {
        return attendanceRepository.findById(id)
                .map(attendance -> {
                    attendance.setEmpId(attendanceRequest.getEmpId());
                    attendance.setFirstName(attendanceRequest.getFirstName());
                    attendance.setLastName(attendanceRequest.getLastName());
                    attendance.setRole(attendanceRequest.getRole());
                    attendance.setAttendanceDate(attendanceRequest.getAttendanceDate());
                    attendance.setTimeIn(attendanceRequest.getTimeIn());
                    attendance.setTimeOut(attendanceRequest.getTimeOut());
                    attendance.setStatus(attendanceRequest.getStatus());

                    attendanceRepository.save(attendance);
                    return ResponseEntity.ok(convertToAttendanceResponse(attendance));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateAttendanceStatus(@PathVariable Long id,
                                                  @RequestParam EAttendanceStatus status) {
        return attendanceRepository.findById(id)
                .map(attendance -> {
                    attendance.setStatus(status);
                    attendanceRepository.save(attendance);
                    return ResponseEntity.ok(convertToAttendanceResponse(attendance));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
        return attendanceRepository.findById(id)
                .map(attendance -> {
                    attendanceRepository.delete(attendance);
                    return ResponseEntity.ok(new MessageResponse("Attendance record deleted successfully"));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/photo")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> uploadAttendancePhoto(
            @PathVariable Long id,
            @RequestParam("photo") MultipartFile photo) {
        try {
            return attendanceRepository.findById(id)
                    .map(attendance -> {
                        try {
                            // Process and store the photo with timestamp
                            LocalDateTime now = LocalDateTime.now();
                            byte[] photoData = photoStorageService.processAndStorePhoto(photo, now);
                            
                            // Update the attendance record
                            attendance.setPhotoData(photoData);
                            attendance.setPhotoTimestamp(now);
                            
                            attendanceRepository.save(attendance);
                            
                            return ResponseEntity.ok(convertToAttendanceResponse(attendance));
                        } catch (IOException e) {
                            return ResponseEntity
                                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new MessageResponse("Error processing photo: " + e.getMessage()));
                        }
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }

    private AttendanceResponse convertToAttendanceResponse(Attendance attendance) {
        AttendanceResponse response = new AttendanceResponse();
        response.setId(attendance.getId());
        response.setEmpId(attendance.getEmpId());
        response.setFirstName(attendance.getFirstName());
        response.setLastName(attendance.getLastName());
        response.setRole(attendance.getRole());
        response.setAttendanceDate(attendance.getAttendanceDate());
        response.setTimeIn(attendance.getTimeIn());
        response.setTimeOut(attendance.getTimeOut());
        response.setStatus(attendance.getStatus());
        
        // Include photo data if available
        if (attendance.getPhotoData() != null) {
            response.setPhotoBase64(Base64.getEncoder().encodeToString(attendance.getPhotoData()));
            response.setPhotoTimestamp(attendance.getPhotoTimestamp());
        }
        
        return response;
    }
}
