package com.example.AdminService.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "admin_sv")
@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String user_name;
    private String password;
}
