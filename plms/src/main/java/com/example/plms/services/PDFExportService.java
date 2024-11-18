package com.example.plms.services;

import com.example.plms.models.Product;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;

@Service
public class PDFExportService {

    public void exportProductsToPDF(List<Product> products, OutputStream outputStream) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Title
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Product Lifecycle Report");
            contentStream.newLine();
            contentStream.endText();

            // Table Header
            float margin = 50;
            float yStart = 700;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float[] columnWidths = {100, 200, 100}; // Adjusted for three columns
            float rowHeight = 20;

            drawTableHeader(contentStream, yStart, margin, columnWidths, rowHeight);

            // Table Data
            float yPosition = yStart - rowHeight;
            for (Product product : products) {
                if (yPosition < margin) {
                    // Add a new page if space runs out
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = yStart;
                    drawTableHeader(contentStream, yStart, margin, columnWidths, rowHeight);
                    yPosition -= rowHeight;
                }

                drawRow(contentStream, yPosition, margin, columnWidths, rowHeight, product);
                yPosition -= rowHeight;
            }

            contentStream.close();
            document.save(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawTableHeader(PDPageContentStream contentStream, float y, float margin, float[] columnWidths, float rowHeight) throws Exception {
        float x = margin;

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.setLeading(rowHeight);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText("Name");
        contentStream.newLineAtOffset(columnWidths[0], 0);
        contentStream.showText("Description");
        contentStream.newLineAtOffset(columnWidths[1], 0);
        contentStream.showText("Stage");
        contentStream.endText();
    }

    private void drawRow(PDPageContentStream contentStream, float y, float margin, float[] columnWidths, float rowHeight, Product product) throws Exception {
        float x = margin;

        contentStream.setFont(PDType1Font.HELVETICA, 10);
        contentStream.beginText();
        contentStream.setLeading(rowHeight);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(product.getName());
        contentStream.newLineAtOffset(columnWidths[0], 0);
        contentStream.showText(product.getDescription() != null ? product.getDescription() : "N/A");
        contentStream.newLineAtOffset(columnWidths[1], 0);
        contentStream.showText(product.getLifecycleStage().getName());
        contentStream.endText();
    }
}