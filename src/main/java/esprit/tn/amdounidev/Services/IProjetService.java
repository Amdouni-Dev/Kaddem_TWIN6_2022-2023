package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;

import java.util.List;

public interface IProjetService {

    Projet addProjet(Projet d);
    List<Projet> addProjet(List<Projet> listProjet);

    Projet updateProjet(Projet d);
    List<Projet> updateProjet(List<Projet> listProjet);

    void deleteProjet(Long id);
    void deleteProjet(Projet d);

    List<Projet> findAllProjet();
    Projet findProjetById(Long id);
    public void aassignProjetToTache(Long idProjet, Long idTache);

    public List<Tache> getTachesByProjet(Long idProjet);
}
