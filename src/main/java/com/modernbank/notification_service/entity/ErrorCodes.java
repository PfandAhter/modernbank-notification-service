package com.modernbank.notification_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "error_codes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ErrorCodes {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "error")
    private String error;

    @Column(name = "description")
    private String description;

}