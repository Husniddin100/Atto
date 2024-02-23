package org.example.dto;


import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;

import java.time.LocalDateTime;

public class Profile {
//    name,surname,phone unique,pswd,created_date,status,role

    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private LocalDateTime createdDate;
    private GeneralStatus status;
    private ProfileRole role;

    public Profile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public ProfileRole getRole() {
        return role;
    }

    public void setRole(ProfileRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", status=" + status +
                ", role=" + role +
                '}';
    }
}
