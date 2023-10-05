package com.example.RoleService.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "role")
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role_name;
}
