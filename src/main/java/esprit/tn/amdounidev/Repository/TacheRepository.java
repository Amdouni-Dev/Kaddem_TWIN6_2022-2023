package esprit.tn.amdounidev.Repository;


import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Etudiant;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {
    @Query("SELECT t FROM Tache t WHERE t.idTache = ?1")
    public Tache findByIdTache(Long id);


    @Query("SELECT t FROM Tache t WHERE t.descriptionTache = ?1")
    public Tache findByNomTache(String nom);


    @Query("SELECT t FROM Tache t,Projet  p WHERE t.projet.idProjet=p.idProjet and p.idProjet = ?1")
    public List<Tache> findByTachesByProjet(Long id);


    @Query("SELECT t FROM Tache t,Projet  p WHERE t.projet.idProjet=p.idProjet and p.nomProjet = ?1")
    public List<Tache> findByTachesByNameProjet(String nom);


    @Query("SELECT e FROM Etudiant e,Tache t WHERE t.etudiant.idE=e.idE and t.idTache = ?1")
    public Etudiant findEtudiantByTache(Long id);
}
