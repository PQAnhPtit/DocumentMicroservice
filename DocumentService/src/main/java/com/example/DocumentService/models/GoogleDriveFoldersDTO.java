package com.example.DocumentService.models;


import lombok.Data;

import java.io.Serializable;

@Data
public class GoogleDriveFoldersDTO implements Serializable {
    private String id;
    private String name;
    private String link;
}
