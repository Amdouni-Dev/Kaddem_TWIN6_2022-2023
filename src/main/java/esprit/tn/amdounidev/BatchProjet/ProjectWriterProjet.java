package esprit.tn.amdounidev.BatchProjet;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;




import java.util.List;

@Slf4j
public class ProjectWriterProjet implements ItemWriter<Projet>  {



    public void write(List<? extends Projet> projets) {
        log.info("dans cette étape, nous pourrons stocker nos informations" +
                "dans une autre base de données, un fichier externe ou la meme" +
                " base de données si nous le souhaitons");
        for (Projet projet : projets) {
            for (Tache t : projet.getTaches()) {
                log.info("Tache done ");
            }
        }
    }
}
