package com.wolverhampton.campushub.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transport_schedules")
public class TransportSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromCampus;

    @Column(nullable = false)
    private String toCampus;

    private String departureTime;
    private String arrivalTime;
    private String frequency;
    private String daysOfOperation;
    private String routeInfo;
    private boolean active = true;
    private String alertMessage;
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFromCampus() { return fromCampus; }
    public void setFromCampus(String fromCampus) { this.fromCampus = fromCampus; }
    public String getToCampus() { return toCampus; }
    public void setToCampus(String toCampus) { this.toCampus = toCampus; }
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getDaysOfOperation() { return daysOfOperation; }
    public void setDaysOfOperation(String daysOfOperation) { this.daysOfOperation = daysOfOperation; }
    public String getRouteInfo() { return routeInfo; }
    public void setRouteInfo(String routeInfo) { this.routeInfo = routeInfo; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public String getAlertMessage() { return alertMessage; }
    public void setAlertMessage(String alertMessage) { this.alertMessage = alertMessage; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
