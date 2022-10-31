package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Reclamation;

import java.util.List;

public interface IReclamationService {

    Reclamation saveReclamation(Reclamation reclamation);
    Reclamation updateReclamation(Reclamation reclamation, Long id);
    void deleteReclamationById(Long id);
    void deleteReclamation(Reclamation reclamation);
    List<Reclamation> listeReclamations();
    Reclamation findReclamationById(Long id);
}
