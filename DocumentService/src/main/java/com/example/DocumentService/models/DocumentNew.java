package com.example.DocumentService.models;

import com.example.DocumentService.entity.Document;
import lombok.Data;

import java.io.Serializable;

@Data
public class DocumentNew implements Serializable {
    private int id;
    private Document document;
    private String name;
    private String cate_name;
    private String date;
}
