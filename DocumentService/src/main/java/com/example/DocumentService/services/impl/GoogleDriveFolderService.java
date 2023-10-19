package com.example.DocumentService.services.impl;

import com.example.DocumentService.models.GoogleDriveFileDTO;
import com.example.DocumentService.models.GoogleDriveFoldersDTO;
import com.example.DocumentService.services.IGoogleDriveFolder;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleDriveFolderService implements IGoogleDriveFolder {

    @Autowired
    GoogleFileManager googleFileManager;

    @Override
    public List<GoogleDriveFoldersDTO> getAllFolder() throws IOException, GeneralSecurityException {

        List<File> files = googleFileManager.listFolderContent1("root");
        List<GoogleDriveFoldersDTO> responseList = null;
        GoogleDriveFoldersDTO dto = null;

        if (files != null) {
            responseList = new ArrayList<>();
            for (File f : files) {
                dto = new GoogleDriveFoldersDTO();
                dto.setId(f.getId());
                dto.setName(f.getName());
                dto.setLink("https://drive.google.com/drive/u/3/folders/"+f.getId());
                responseList.add(dto);
            }
        }
        return responseList;
    }

    public List<GoogleDriveFoldersDTO> getAllFolderLink(String parentId) throws IOException, GeneralSecurityException {
        List<File> files = googleFileManager.listFolderContent1(parentId);
        List<GoogleDriveFoldersDTO> responseList = null;
        GoogleDriveFoldersDTO dto = null;

        if (files != null) {
            responseList = new ArrayList<>();
            for (File f : files) {
                dto = new GoogleDriveFoldersDTO();
                boolean check = true;
                List<GoogleDriveFileDTO> responseList123 = null;
                List<File> files123 = googleFileManager.listEverything123(parentId);
                if (files123 != null) {
                    for (File f1 : files123) {
                        if (f1.getSize() != null) {
                            if(f1.getName().equals(f.getName())){
                                check = false;
                            }
                        }
                    }
                }
                if(check){
                    dto.setId(f.getId());
                    dto.setName(f.getName());
                    dto.setLink("https://drive.google.com/drive/u/3/folders/"+f.getId());
                    responseList.add(dto);
                }
            }
        }
        return responseList;
    }
}
