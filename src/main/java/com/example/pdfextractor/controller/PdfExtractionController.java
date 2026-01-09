package com.example.pdfextractor.controller;

import com.example.pdfextractor.model.ExtractionResponse;
import com.example.pdfextractor.service.PdfExtractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/pdf")
public class PdfExtractionController {

    private final PdfExtractionService extractionService;

    public PdfExtractionController(PdfExtractionService extractionService) {
        this.extractionService = extractionService;
    }

    @PostMapping("/extract")
    public ResponseEntity<ExtractionResponse> extract(
            @RequestParam("file") MultipartFile file) {

        ExtractionResponse response = extractionService.extract(file);
        return ResponseEntity.ok(response);
    }
}
