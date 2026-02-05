package com.devnexus.backend.service;

import com.devnexus.backend.dto.AsesoriaDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public ByteArrayInputStream generarReporteAsesorias(List<AsesoriaDTO> asesorias) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
            Paragraph titulo = new Paragraph("Reporte de Asesorías - DevNexus", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 3, 5, 3, 5}); 

            String[] headers = {"Fecha", "Hora", "Estudiante", "Estado", "Mensaje/Tema"};
            for (String header : headers) {
                PdfPCell hcell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                hcell.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(hcell);
            }

            for (AsesoriaDTO app : asesorias) {
                table.addCell(app.getDate() != null ? app.getDate() : "");
                
                table.addCell(app.getTime() != null ? app.getTime() : "");
                
                table.addCell(app.getClientEmail() != null ? app.getClientEmail() : "Sin Email");
                
                table.addCell(app.getStatus() != null ? app.getStatus() : "Pendiente");
                
                table.addCell(app.getTopic() != null ? app.getTopic() : "Sin tema");
            }

            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            System.err.println("Error generando PDF: " + ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}