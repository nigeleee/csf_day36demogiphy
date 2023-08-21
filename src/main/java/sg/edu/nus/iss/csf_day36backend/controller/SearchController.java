package sg.edu.nus.iss.csf_day36backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import sg.edu.nus.iss.csf_day36backend.service.SearchService;

@RestController
@RequestMapping(path="/api")
@CrossOrigin
public class SearchController {

    @Autowired
    private SearchService service;
    
    @GetMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> search(@RequestParam String q) {
       List<String> images = service.searchGif(q);

       return ResponseEntity.ok(Json.createArrayBuilder(images).build().toString());

}

}
