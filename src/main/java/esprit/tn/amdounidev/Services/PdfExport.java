package esprit.tn.amdounidev.Services;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import esprit.tn.amdounidev.entities.Projet;
import org.springframework.stereotype.Service;


@Service
public class PdfExport {





    private List<Projet> listProjets;

    public PdfExport(List<Projet> listProjets) {
        this.listProjets= listProjets;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);



        cell.setPhrase(new Phrase("Nom projet", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Type projet", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Durée projet", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date début projet", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date fin projet", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Projet p : listProjets) {

            table.addCell(p.getNomProjet());
            table.addCell(String.valueOf(p.getTypeProjet()));
            table.addCell(p.getDureeProjet());
            table.addCell(String.valueOf(p.getDateDebutP()));
            table.addCell(String.valueOf(p.getDateFinP()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.lightGray);

        Paragraph p = new Paragraph("Liste des projets", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
