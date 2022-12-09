package esprit.tn.amdounidev.helper;

import esprit.tn.amdounidev.entities.Equipe;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Nom Equipe", "Niveau","salle","thematique",  "Valide?","Nombre des membres", "Nom du responsable","date Creation","date activation"};
    static String SHEET = "Equipes";

    public static ByteArrayInputStream equipesToExcel(List<Equipe> equipes) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Equipe equipe : equipes) {
                Row row = sheet.createRow(rowIdx++);
//                static String[] HEADERs = { "Id", "Nom Equipe", "Niveau","salle","thematique",
//                "Valide?","Nombre des membres", "Nom du responsable","date Creation","date activation"};

                row.createCell(0).setCellValue(equipe.getIdEquipe());
                row.createCell(1).setCellValue(equipe.getNomEquipe());
                row.createCell(2).setCellValue(equipe.getNiveau().toString());
                row.createCell(3).setCellValue(equipe.getDetailEquipe().getSalle());
                row.createCell(4).setCellValue(equipe.getDetailEquipe().getThematique());
                row.createCell(5).setCellValue(equipe.getIsValid());
                row.createCell(6).setCellValue(equipe.getEtudiants().size());

                if(equipe.getEtudiant()!=null){
                    row.createCell(7).setCellValue(equipe.getEtudiant().getNom());

                }
                else{

                    row.createCell(7).setCellValue("pas de responsable ");

                }

             //   row.createCell(7).setCellValue(equipe.getEtudiant().getNom());
                row.createCell(8).setCellValue(equipe.getDetailEquipe().getDateCreation().toString());
                row.createCell(9).setCellValue(equipe.getDetailEquipe().getDateActivation().toString());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
