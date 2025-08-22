package com.pm.patientservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationMessageDTO {
    @JsonProperty("patientId")
    private UUID patientId;

    @JsonProperty("patientName")
    private String patientName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("eventType")
    private String eventType;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    public NotificationMessageDTO() {
        this.timestamp = LocalDateTime.now();
    }

    public NotificationMessageDTO(UUID patientId, String patientName, String email, String eventType) {
        this();
        this.patientId = patientId;
        this.patientName = patientName;
        this.email = email;
        this.eventType = eventType;
    }

    // Getters and Setters
    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PatientNotificationMessage{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", email='" + email + '\'' +
                ", eventType='" + eventType + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
