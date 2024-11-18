package com.example.plms.controllers;

import com.example.plms.models.Product;
import com.example.plms.services.PDFExportService;
import com.example.plms.services.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportingController {

    private final ReportingService reportingService;
    private final PDFExportService pdfExportService;

    @Autowired
    public ReportingController(ReportingService reportingService, PDFExportService pdfExportService) {
        this.reportingService = reportingService;
        this.pdfExportService = pdfExportService;
    }

    @GetMapping("/stage/{stageName}")
    public ResponseEntity<byte[]> generateStageReport(@PathVariable String stageName) {
        try {
            List<Product> products = reportingService.getProductsByStage(stageName);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            pdfExportService.exportProductsToPDF(products, out);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=stage-report.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(out.toByteArray());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(("Invalid Stage Name: " + stageName).getBytes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}