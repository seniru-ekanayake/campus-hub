package com.wolverhampton.campushub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDTO {

    public static class RegisterRequest {
        @NotBlank private String username;
        @NotBlank @Email private String email;
        @NotBlank @Size(min = 6) private String password;
        private String firstName;
        private String lastName;
        private String studentId;
        private String course;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getStudentId() { return studentId; }
        public void setStudentId(String studentId) { this.studentId = studentId; }
        public String getCourse() { return course; }
        public void setCourse(String course) { this.course = course; }
    }

    public static class LoginRequest {
        @NotBlank private String username;
        @NotBlank private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginResponse {
        private String token;
        private String username;
        private String email;
        private String role;
        private Long userId;
        private String firstName;
        private String lastName;

        public LoginResponse(String token, String username, String email, String role,
                             Long userId, String firstName, String lastName) {
            this.token = token;
            this.username = username;
            this.email = email;
            this.role = role;
            this.userId = userId;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getToken() { return token; }
        public String getUsername() { return username; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public Long getUserId() { return userId; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
    }
}
