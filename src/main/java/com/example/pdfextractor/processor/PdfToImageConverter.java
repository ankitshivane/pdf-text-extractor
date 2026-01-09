package com.example.pdfextractor.processor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PdfToImageConverter {

    private static final int DPI = 300;

    private PdfToImageConverter() {
        // Utility class - prevent instantiation
    }

    public static List<BufferedImage> convert(MultipartFile file) {

        List<BufferedImage> images = new ArrayList<>();

        try (PDDocument document = PDDocument.load(file.getInputStream())) {

            PDFRenderer renderer = new PDFRenderer(document);

            for (int page = 0; page < document.getNumberOfPages(); page++) {
                BufferedImage image =
                        renderer.renderImageWithDPI(page, DPI);
                images.add(image);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to convert PDF to images", e);
        }

        return images;
    }
}
