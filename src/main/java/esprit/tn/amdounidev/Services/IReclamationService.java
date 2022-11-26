package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Reclamation;

import java.util.List;

public interface IReclamationService {

    Reclamation saveReclamation(Reclamation reclamation);
    List<Reclamation> saveReclamations(List<Reclamation> listReclamations);
    Reclamation updateReclamation(Reclamation reclamation, Long id);
    List<Reclamation> updateReclamations(List<Reclamation> listReclamations);
    void deleteReclamationById(Long id);
    void deleteReclamation(Reclamation reclamation);
    List<Reclamation> listeReclamations();
    Reclamation findReclamationById(Long id);
}
