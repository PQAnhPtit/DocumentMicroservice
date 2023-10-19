package com.example.DocumentService.services;

import com.example.DocumentService.models.GoogleDriveFoldersDTO;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface IGoogleDriveFolder {
    List<GoogleDriveFoldersDTO> getAllFolder() throws IOException, GeneralSecurityException;
}
