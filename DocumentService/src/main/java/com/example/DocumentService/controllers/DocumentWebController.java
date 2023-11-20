package com.example.DocumentService.controllers;

import com.example.DocumentService.config.GoogleDriveConfig;
import com.example.DocumentService.entity.CategoryDocument;
import com.example.DocumentService.entity.Document;
import com.example.DocumentService.models.GoogleDriveFileDTO;
import com.example.DocumentService.models.GoogleDriveFoldersDTO;
import com.example.DocumentService.services.ICategoryDocumentService;
import com.example.DocumentService.services.IDocumentService;
import com.example.DocumentService.services.impl.GoogleDriveFileService;
import com.example.DocumentService.services.impl.GoogleDriveFolderService;
import com.google.api.services.drive.Drive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class DocumentWebController {
    private final RestTemplate restTemplate;

    public DocumentWebController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private IDocumentService documentService;

    @Autowired
    private GoogleDriveConfig googleDriveConfig;

    @Autowired
    private ICategoryDocumentService categoryDocumentService;

    @Autowired
    GoogleDriveFileService googleDriveFileService;

    @Autowired
    GoogleDriveFolderService googleDriveFolderService;

    @GetMapping("/listFileDriver")
    public String pageIndex(Model model) throws IOException, GeneralSecurityException {
        Drive drive = googleDriveConfig.getInstance();
        return "<h1>Hello</h1>";
    }
    @PostMapping("/driver-list-file/{parentId}")
    public  List<GoogleDriveFileDTO> pageIndex1(@PathVariable String parentId, Model model) throws IOException, GeneralSecurityException {
        List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile123(parentId);
        return listFile;
    }

    @PostMapping("/driver-list-folder/{parentId}")
    public List<GoogleDriveFoldersDTO> listFolder(@PathVariable String parentId, Model model) throws IOException, GeneralSecurityException {
        List<GoogleDriveFoldersDTO> listFolder = googleDriveFolderService.getAllFolderLink(parentId);
        //List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile123(parentId);
        return listFolder;
    }

    @GetMapping("/getDocument")
    public Document getCategoryDocument(@RequestParam("link") String link) {
        ArrayList<Document> Documents = documentService.getAll();
        int id = 0;
        for(Document file: Documents){
            if(file.getLink().equals(link)){
                id = file.getId();
                break;
            }
        }
        return documentService.getById(id).get();
    }

    @GetMapping("/up-len-kho/{user_id}/{document_id}/{category_id}")
    public CategoryDocument getCategoryDocument(@PathVariable int user_id,
                                                @PathVariable int document_id,
                                                @PathVariable int category_id) {
        CategoryDocument categoryDocument = new CategoryDocument();
        categoryDocument.setCategory_id(category_id);
        categoryDocument.setDocument_id(document_id);
        categoryDocument.setUser_id(user_id);
        return categoryDocumentService.save(categoryDocument);
    }

    @GetMapping("/kho-tai-lieu/{category_id}")
    public ArrayList<Document> getCategoryDocumentByID(@PathVariable int category_id) {
        ArrayList<Document> documents = new ArrayList<>();
        ArrayList<CategoryDocument> categoryDocuments = categoryDocumentService.getAll();
        for(CategoryDocument a: categoryDocuments){
            if(a.getCategory_id() == category_id){
                documents.add(documentService.getById(a.getDocument_id()).get());
            }
        }
        return documents;
    }

/*    @GetMapping("/uploadFileGDriver/{id}")
    public String uploadFileGDriver(@PathVariable String id, HttpServletRequest request, Model model) throws Exception {
        String fileName = "";
        String link = "";
        List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile();
        GoogleDriveFileDTO dto = new GoogleDriveFileDTO();
        for (GoogleDriveFileDTO file: listFile) {
            if(file.getId().equals(id)){
                fileName += file.getName();
                link += file.getLink();
                break;
            }
        }
        return link;
    }*/

/*
    @GetMapping("/vd")
    public ResponseEntity<ArrayList<Document>> getAllDocument() {
        String apiUrl = "http://localhost:7716/document";

        ResponseEntity<ArrayList<Document>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Document>>() {}
        );

        ArrayList<Document> responseBody = response.getBody();
        return ResponseEntity.ok(responseBody);
    }
*/

    /*@GetMapping("/uploadFileGDriver")
    public String uploadFileGDriver(@RequestParam("id") String id, HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("file_id", id);
        String fileName = "";
        List<GoogleDriveFileDTO> listFile = googleDriveFileService.getAllFile();
        GoogleDriveFileDTO dto = new GoogleDriveFileDTO();
        for (GoogleDriveFileDTO file: listFile) {
            if(file.getId().equals(id)){
                fileName += file.getName();
                break;
            }
        }
        model.addAttribute("fileName", fileName);
        model.addAttribute("obj", new Document());
        int checkCookie = 0;
        ArrayList<Category> list = categoryService.getAll();
        model.addAttribute("list_category", list);
        boolean check = false;
        Cookie[] cookies = request.getCookies();
        String cookieName = "user_id";
        if(cookies == null){
            return "web/document/upl-gdri";
        }else{
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(cookie.getName().equals(cookieName)){
                    check = true;
                    break;
                }
            }
            if(check == true){
                checkCookie = 1;
                model.addAttribute("checkCookie", checkCookie);
                return "web/document/upl-gdri";
            }
        }
        return "web/document/upl-gdri";
    }*/
}
