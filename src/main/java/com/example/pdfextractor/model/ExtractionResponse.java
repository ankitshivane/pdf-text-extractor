package com.example.pdfextractor.model;

public record ExtractionResponse(
        String fileName,
        String extractionType,
        String content
) {
    public static ExtractionResponse ocr(String file, String text) {
        return new ExtractionResponse(file, "OCR", text);
    }

    public static ExtractionResponse text(String file, String text) {
        return new ExtractionResponse(file, "TEXT", text);
    }
}
