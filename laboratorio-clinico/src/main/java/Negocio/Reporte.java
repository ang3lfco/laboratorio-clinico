/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.ReporteDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public class Reporte {
    public void Reporte(String fechaInicio, String fechaFinal, List<ReporteDTO> lista) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fc.showOpenDialog(fc);
        File name = fc.getSelectedFile();
        String path = name.getAbsolutePath();
        
        Document document = new Document(PageSize.A4); // Cambiamos a A4 para más espacio
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path+".pdf"));
            document.open();
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font fontSubTitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
            Font fontContenido = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

            // Título del reporte
            Paragraph titulo = new Paragraph("Reporte", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(" ")); // Espacio en blanco

            // Filtros aplicados
            Paragraph filtros = new Paragraph("Filtros aplicados:", fontSubTitulo);
            document.add(filtros);

            PdfPTable tableFiltros = new PdfPTable(2);
            tableFiltros.setWidthPercentage(100);
            tableFiltros.addCell(new Phrase("Desde:", fontContenido));
            tableFiltros.addCell(new Phrase(fechaInicio, fontContenido));
            tableFiltros.addCell(new Phrase("Hasta:", fontContenido));
            tableFiltros.addCell(new Phrase(fechaFinal, fontContenido));
            document.add(tableFiltros);

            document.add(new Paragraph(" ")); // Espacio en blanco

            // Tabla de pagos
            

            PdfPTable table = new PdfPTable(4); // columnas para los datos de Pago
            table.setWidthPercentage(100);

            // Encabezados de la tabla
            String[] headers = {"Cliente","Analisis","folio","Fecha"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, fontContenido));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
            }
            // Datos de la tabla
                
            for (int i = 0; i < lista.size(); i++) {
                    
                    table.addCell(new Phrase(lista.get(i).getCliente(), fontContenido));
                    table.addCell(new Phrase(lista.get(i).getPrueba(), fontContenido));
                    table.addCell(new Phrase(String.valueOf(lista.get(i).getAnalisis()) , fontContenido));
                    table.addCell(new Phrase(lista.get(i).getFechaHora().toString(), fontContenido));
            }
//            for (int i = 0; i < model.getRowCount(); i++) {
//                for (int j = 0; j < model.getColumnCount(); j++) {
//                    table.addCell(new Phrase(model.getValueAt(i, j).toString(), fontContenido));
//                }
//            }

            document.add(table);
            

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

    }
    
    public void createPDF(File pdfNewFile) {
        // Aquí introduciremos el código para crear el PDF.
    }
    
    public void ruta(){
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fc.showOpenDialog(fc);
        File name = fc.getSelectedFile();
        String path = name.getAbsolutePath();
        System.out.println(path);  
    }
    
    
}
