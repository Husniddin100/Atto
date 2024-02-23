package org.example.dto;


import org.example.enums.GeneralStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Card {
    private Integer id;
    private String cardNumber;
    private LocalDate expDate;
    private Double balance;
    private GeneralStatus status;
    private String phone;
    private LocalDateTime createdDate;

    public Card() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expDate=" + expDate +
                ", balance=" + balance +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
