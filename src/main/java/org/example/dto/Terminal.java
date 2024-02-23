package org.example.dto;


import org.example.enums.GeneralStatus;

import java.time.LocalDateTime;

public class Terminal {
    private Integer id;
    private String code;
    private String address;
    private GeneralStatus status;
    private LocalDateTime createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", createdDate=" + createdDate +
                '}';
    }
}
