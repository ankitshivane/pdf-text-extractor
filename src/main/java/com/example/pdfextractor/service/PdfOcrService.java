package com.example.pdfextractor.service;

import com.example.pdfextractor.exception.PdfProcessingException;
import com.example.pdfextractor.processor.PdfToImageConverter;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.List;

@Service
public class PdfOcrService {

    @Value("${tesseract.datapath}")
    private String tessDataPath;

    @Value("${tesseract.language}")
    private String language;
//    @Async
    public String extract(MultipartFile file) {
        try {
            List<BufferedImage> images = PdfToImageConverter.convert(file);
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath(tessDataPath);
            tesseract.setLanguage(language);


            StringBuilder result = new StringBuilder();
            for (BufferedImage image : images) {
                result.append(tesseract.doOCR(image));
            }
            return result.toString();

        } catch (Exception e) {
            throw new PdfProcessingException("OCR failed", e);
        }
    }

}
