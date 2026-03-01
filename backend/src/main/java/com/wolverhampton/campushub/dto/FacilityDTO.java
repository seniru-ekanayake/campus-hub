package com.wolverhampton.campushub.dto;

import com.wolverhampton.campushub.entity.Facility;
import java.time.LocalDateTime;

public class FacilityDTO {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String campus;
    private String openingTimes;
    private String imageUrl;
    private Facility.FacilityStatus status;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getCampus() { return campus; }
    public void setCampus(String campus) { this.campus = campus; }
    public String getOpeningTimes() { return openingTimes; }
    public void setOpeningTimes(String openingTimes) { this.openingTimes = openingTimes; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Facility.FacilityStatus getStatus() { return status; }
    public void setStatus(Facility.FacilityStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
