package com.example.DocumentService.models;

import lombok.Data;
import java.io.Serializable;

@Data
public class GoogleDriveFileDTO implements Serializable {
    private String id;
    private String name;
    private String link;
    private String size;
    private String thumbnailLink;
    private boolean shared;

    public GoogleDriveFileDTO() {

    }
}