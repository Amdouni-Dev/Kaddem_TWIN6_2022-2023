package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Reclamation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReclamationService {

    Reclamation saveReclamation(Reclamation reclamation);
    List<Reclamation> saveReclamations(List<Reclamation> listReclamations);
    Reclamation updateReclamation(Reclamation reclamation, Long id);
    List<Reclamation> updateReclamations(List<Reclamation> listReclamations);
    void deleteReclamationById(Long id);
    void deleteReclamation(Reclamation reclamation);
    Page<Reclamation> listeReclamations(Pageable pageable);

    List<Reclamation> listeReclamationsNonTratitees();
    List<Reclamation> listeReclamationsTratitees();
    Reclamation traiterRec(Long id, Reclamation reclamation);
    Reclamation findReclamationById(Long id);
}
