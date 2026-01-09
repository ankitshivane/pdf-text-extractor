package com.example.pdfextractor.processor;

import com.example.pdfextractor.model.ExtractionResponse;
import com.example.pdfextractor.service.PdfOcrService;
import com.example.pdfextractor.service.PdfTextService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class PdfAnalyzer {

    private final PdfTextService textService;
    private final PdfOcrService ocrService;

    public PdfAnalyzer(PdfTextService textService,
                       PdfOcrService ocrService) {
        this.textService = textService;
        this.ocrService = ocrService;
    }

    public ExtractionResponse analyze(MultipartFile file) {

        String text = textService.extract(file);

        if (text.isBlank()) {
            String ocrText = ocrService.extract(file);
            return ExtractionResponse.ocr(file.getOriginalFilename(), ocrText);
        }

        return ExtractionResponse.text(file.getOriginalFilename(), text);
    }
}
