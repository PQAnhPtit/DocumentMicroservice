package com.example.DocumentService.controllers;

import com.example.DocumentService.config.GoogleDriveConfig;
import com.example.DocumentService.entity.CategoryDocument;
import com.example.DocumentService.entity.Document;
import com.example.DocumentService.models.DocumentNew;
import com.example.DocumentService.models.GoogleDriveFileDTO;
import com.example.DocumentService.models.GoogleDriveFoldersDTO;
import com.example.DocumentService.services.ICategoryDocumentService;
import com.example.DocumentService.services.IDocumentService;
import com.example.DocumentService.services.impl.GoogleDriveFileService;
import com.example.DocumentService.services.impl.GoogleDriveFolderService;
import com.google.api.services.drive.Drive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        categoryDocument.setDate_post(formattedDate);
        return categoryDocumentService.save(categoryDocument);
    }

    @GetMapping("/kho-tai-lieu/{category_id}")
    public ArrayList<CategoryDocument> getCategoryDocumentByID(@PathVariable int category_id) {
        ArrayList<CategoryDocument> documents = new ArrayList<>();
        ArrayList<CategoryDocument> categoryDocuments = categoryDocumentService.getAll();
        for(CategoryDocument a: categoryDocuments){
            if(a.getCategory_id() == category_id){
                documents.add(a);
            }
        }
        return documents;
    }

    @GetMapping("/category-document/{id}")
    public Document getCategoryDocumentNew(@PathVariable int id) {
        CategoryDocument categoryDocument = categoryDocumentService.getById(id).get();
        Document document = documentService.getById(categoryDocument.getDocument_id()).get();
        return document;
    }


    @GetMapping("/kho-tai-lieu-home")
    public ArrayList<CategoryDocument> getCategoryDocumentHomeByID() {
        ArrayList<CategoryDocument> categoryDocuments = categoryDocumentService.getAll();
        return categoryDocuments;
    }

    @GetMapping("/kho-tai-lieu-my-page/{user_id}")
    public ArrayList<CategoryDocument> getCategoryDocumentMyPageByID(@PathVariable int user_id) {
        ArrayList<CategoryDocument> categoryDocuments = categoryDocumentService.getAll();
        ArrayList<CategoryDocument> categoryDocumentNew = new ArrayList<>();
        for(CategoryDocument a: categoryDocuments){
            if(a.getUser_id() == user_id){
                categoryDocumentNew.add(a);
            }
        }
        return categoryDocumentNew;
    }

    @GetMapping("/my-page/{user_id}")
    public ArrayList<Document> getMyPage(@PathVariable int user_id) {
        ArrayList<Document> documents = new ArrayList<>();
        ArrayList<CategoryDocument> categoryDocuments = categoryDocumentService.getAll();
        for(CategoryDocument a: categoryDocuments){
            if(a.getUser_id() == user_id){
                documents.add(documentService.getById(a.getDocument_id()).get());
            }
        }
        return documents;
    }

    @DeleteMapping("/my-page/delete/{user_id}/{document_id}")
    public ResponseEntity<Document> deleteDocument(@PathVariable int user_id,
                                                   @PathVariable int document_id) {
        ArrayList<CategoryDocument> categoryDocuments = categoryDocumentService.getAll();
        for(CategoryDocument a: categoryDocuments){
            if(a.getUser_id() == user_id && a.getDocument_id() == document_id){
                categoryDocumentService.delete(a.getId());
                documentService.delete(document_id);
                break;
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ArrayList<Document> Search(@PathVariable String name){
        ArrayList<Document> documentNew = new ArrayList<>();
        ArrayList<Document> documents = documentService.getAll();
        if(!name.equals("")){
            for (Document doc: documents) {
                if(doc.getName().toLowerCase().contains(name.toLowerCase())){
                    documentNew.add(doc);
                }
            }
        }
        return documentNew;
    }

    @DeleteMapping("/category-document/{id}")
    public ResponseEntity<CategoryDocument> deleteCategoryDocument(@PathVariable int id) {
        CategoryDocument categoryDocumentNew = categoryDocumentService.getById(id).get();
        if(categoryDocumentNew != null){
            categoryDocumentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
