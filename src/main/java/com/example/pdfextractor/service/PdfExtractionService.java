package com.example.pdfextractor.service;

import com.example.pdfextractor.model.ExtractionResponse;
import com.example.pdfextractor.processor.PdfAnalyzer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfExtractionService {

    private final PdfAnalyzer analyzer;

    public PdfExtractionService(PdfAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public ExtractionResponse extract(MultipartFile file) {
        return analyzer.analyze(file);
    }
}
