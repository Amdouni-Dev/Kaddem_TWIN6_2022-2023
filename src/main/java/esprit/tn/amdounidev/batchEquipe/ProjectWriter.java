package esprit.tn.amdounidev.batchEquipe;


import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Etudiant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Slf4j

public class ProjectWriter implements ItemWriter<Equipe> {
   /*tod6*/

    /* 13. écrire nos données dans la base de données*/
    public void write(List<? extends Equipe> equipes) {
        log.info("dans cette étape, nous pourrons stocker nos informations" +
                "dans une autre base de données, un fichier externe ou la meme" +
                " base de données si nous le souhaitons");
        for (Equipe equipe : equipes) {
            for (Etudiant etudiant : equipe.getEtudiants()) {
                etudiant.getContrats().forEach(contrat -> {
               /* todo 7   sauvagarder les nouvelles informations (etudiant, date log, nouveau montant
               *   dans la table log*/
                });
            }
        }
    }
}
