package esprit.tn.amdounidev.Services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import esprit.tn.amdounidev.entities.Contrat;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ContratPdfGenerator {

    public void generate(List<Contrat> contractList, HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document

        Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter

        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to change it

        document.open();

        // Creating font

        // Setting font style and size

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        fontTiltle.setSize(20);

        // Creating paragraph

        Paragraph paragraph1 = new Paragraph("List of the Contracts", fontTiltle);

        // Aligning the paragraph in the document

        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in the document

        document.add(paragraph1);

        // Creating a table of the 4 columns

        PdfPTable table = new PdfPTable(5);

        // Setting width of the table, its columns and spacing

        table.setWidthPercentage(100f);

        table.setWidths(new int[] {3,3,3,3,3});

        table.setSpacingBefore(5);

        // Create Table Cells for the table header

        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding of the table cell

        cell.setBackgroundColor(CMYKColor.BLUE);

        cell.setPadding(5);

        // Creating font

        // Setting font style and size

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        font.setColor(CMYKColor.WHITE);

        // Adding headings in the created table cell or  header

        // Adding Cell to table

        cell.setPhrase(new Phrase("ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Specialite Contrat", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Date Debut Contrat", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Date Fin Contrat", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Montant Contrat", font));

        table.addCell(cell);

        // Iterating the list of contracts

        for (Contrat contrat: contractList) {

            table.addCell(String.valueOf(contrat.getIdContrat()));

            table.addCell(String.valueOf(contrat.getSpecailite()));

            table.addCell(String.valueOf(contrat.getDateDebutC()));

            table.addCell(String.valueOf(contrat.getDateFinC()));

            table.addCell(String.valueOf(contrat.getMontantC()));

        }

        // Adding the created table to the document

        document.add(table);

        // Closing the document

        document.close();

    }
}
