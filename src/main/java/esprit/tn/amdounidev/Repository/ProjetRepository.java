package esprit.tn.amdounidev.Repository;


import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Long> {

    @Query("SELECT p FROM Projet p  WHERE p.idProjet = ?1")
    public Projet findByIdProjet(Long id);

    @Query("SELECT t FROM Tache t,Projet  p WHERE t.projet.idProjet=p.idProjet and p.idProjet = ?1")
    public List<Tache> findByTachesByProjets(Long id);
    @Transactional
    @Modifying
    @Query("delete  FROM Projet p  WHERE p.idProjet =22")
    public void deleteAutomatique();


    @Query("SELECT count(p) FROM Projet p  WHERE p.typeProjet = 'PIDEV'")
    public int  findByTypePIDEVProjet();


    @Query("SELECT count(p) FROM Projet p  WHERE p.typeProjet = 'PFE'")
    public int  findByTypePFEProjet();


    @Query("SELECT count(p) FROM Projet p  WHERE p.typeProjet = 'JEUXVIDEO'")
    public int  findByTypeJEUVIDEOProjet();








}
