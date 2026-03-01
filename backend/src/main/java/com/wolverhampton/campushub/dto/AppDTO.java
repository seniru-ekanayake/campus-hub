package com.wolverhampton.campushub.dto;

import java.time.LocalDateTime;

public class AppDTO {

    public static class AnnouncementDTO {
        private Long id;
        private String title;
        private String content;
        private String category;
        private String authorUsername;
        private boolean pinned;
        private LocalDateTime createdAt;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public String getAuthorUsername() { return authorUsername; }
        public void setAuthorUsername(String authorUsername) { this.authorUsername = authorUsername; }
        public boolean isPinned() { return pinned; }
        public void setPinned(boolean pinned) { this.pinned = pinned; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }

    public static class TransportDTO {
        private Long id;
        private String fromCampus;
        private String toCampus;
        private String departureTime;
        private String arrivalTime;
        private String frequency;
        private String daysOfOperation;
        private String routeInfo;
        private boolean active;
        private String alertMessage;

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
    }

    public static class ClubDTO {
        private Long id;
        private String name;
        private String description;
        private String category;
        private String meetingSchedule;
        private String location;
        private String imageUrl;
        private String contactEmail;
        private boolean active;
        private long memberCount;
        private boolean member;
        private LocalDateTime createdAt;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public String getMeetingSchedule() { return meetingSchedule; }
        public void setMeetingSchedule(String meetingSchedule) { this.meetingSchedule = meetingSchedule; }
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
        public String getContactEmail() { return contactEmail; }
        public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }
        public long getMemberCount() { return memberCount; }
        public void setMemberCount(long memberCount) { this.memberCount = memberCount; }
        public boolean isMember() { return member; }
        public void setMember(boolean member) { this.member = member; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }

    public static class CheckInRequest {
        private String locationName;
        private String locationType;

        public String getLocationName() { return locationName; }
        public void setLocationName(String locationName) { this.locationName = locationName; }
        public String getLocationType() { return locationType; }
        public void setLocationType(String locationType) { this.locationType = locationType; }
    }

    public static class CheckInDTO {
        private Long id;
        private String locationName;
        private String locationType;
        private int pointsAwarded;
        private LocalDateTime checkInTime;
        private String username;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getLocationName() { return locationName; }
        public void setLocationName(String locationName) { this.locationName = locationName; }
        public String getLocationType() { return locationType; }
        public void setLocationType(String locationType) { this.locationType = locationType; }
        public int getPointsAwarded() { return pointsAwarded; }
        public void setPointsAwarded(int pointsAwarded) { this.pointsAwarded = pointsAwarded; }
        public LocalDateTime getCheckInTime() { return checkInTime; }
        public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
    }

    public static class RewardDTO {
        private Long id;
        private String name;
        private String description;
        private int pointsRequired;
        private int availableQuantity;
        private boolean active;
        private String locationTypeName;
        private int pointValue;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public int getPointsRequired() { return pointsRequired; }
        public void setPointsRequired(int pointsRequired) { this.pointsRequired = pointsRequired; }
        public int getAvailableQuantity() { return availableQuantity; }
        public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }
        public String getLocationTypeName() { return locationTypeName; }
        public void setLocationTypeName(String locationTypeName) { this.locationTypeName = locationTypeName; }
        public int getPointValue() { return pointValue; }
        public void setPointValue(int pointValue) { this.pointValue = pointValue; }
    }

    public static class CounselorDTO {
        private Long id;
        private String name;
        private String title;
        private String specializations;
        private String bio;
        private String profilePicture;
        private String contactEmail;
        private String availabilityNotes;
        private boolean active;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSpecializations() { return specializations; }
        public void setSpecializations(String specializations) { this.specializations = specializations; }
        public String getBio() { return bio; }
        public void setBio(String bio) { this.bio = bio; }
        public String getProfilePicture() { return profilePicture; }
        public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
        public String getContactEmail() { return contactEmail; }
        public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
        public String getAvailabilityNotes() { return availabilityNotes; }
        public void setAvailabilityNotes(String availabilityNotes) { this.availabilityNotes = availabilityNotes; }
        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }
    }

    public static class SessionBookingRequest {
        private Long counselorId;
        private LocalDateTime sessionDate;
        private String issueDescription;

        public Long getCounselorId() { return counselorId; }
        public void setCounselorId(Long counselorId) { this.counselorId = counselorId; }
        public LocalDateTime getSessionDate() { return sessionDate; }
        public void setSessionDate(LocalDateTime sessionDate) { this.sessionDate = sessionDate; }
        public String getIssueDescription() { return issueDescription; }
        public void setIssueDescription(String issueDescription) { this.issueDescription = issueDescription; }
    }

    public static class CounselingSessionDTO {
        private Long id;
        private Long counselorId;
        private String counselorName;
        private String counselorTitle;
        private Long studentId;
        private String studentUsername;
        private LocalDateTime sessionDate;
        private String issueDescription;
        private String status;
        private LocalDateTime createdAt;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getCounselorId() { return counselorId; }
        public void setCounselorId(Long counselorId) { this.counselorId = counselorId; }
        public String getCounselorName() { return counselorName; }
        public void setCounselorName(String counselorName) { this.counselorName = counselorName; }
        public String getCounselorTitle() { return counselorTitle; }
        public void setCounselorTitle(String counselorTitle) { this.counselorTitle = counselorTitle; }
        public Long getStudentId() { return studentId; }
        public void setStudentId(Long studentId) { this.studentId = studentId; }
        public String getStudentUsername() { return studentUsername; }
        public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }
        public LocalDateTime getSessionDate() { return sessionDate; }
        public void setSessionDate(LocalDateTime sessionDate) { this.sessionDate = sessionDate; }
        public String getIssueDescription() { return issueDescription; }
        public void setIssueDescription(String issueDescription) { this.issueDescription = issueDescription; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }
}
