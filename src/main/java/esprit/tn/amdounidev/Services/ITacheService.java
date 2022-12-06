package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Etudiant;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;

import java.util.List;

public interface ITacheService {
    Tache addTache(Tache d);
    List<Tache> addTache (List<Tache> listTache);

    Tache updateTache (Tache d);
    List<Tache> updateTache (List<Tache> listTache);

    void deleteTache(Long id);
    void deleteTache(Tache d);

    List<Tache> findAllTache();
    Tache findTacheById (Long id);

    public void aassignProjetToTache(Long idProjet, Long idTache);
    public void assignProjetToListTache(Long idProjet, List<Long> ListIdTaches);

    public Projet getProjetByTache(Long idTache);
    public List<Tache> getTachesByProjet(Long idProjet) ;

    public Etudiant getEtudiantByTache(Long idEtudiant) ;
}
