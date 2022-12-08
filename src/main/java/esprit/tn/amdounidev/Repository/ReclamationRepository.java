package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Contrat;
import esprit.tn.amdounidev.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

    @Query("select r from Reclamation r where r.etat=true ")
    List<Reclamation> reclamationsTraitees();
    @Query("select r from Reclamation r where r.etat=false")
    List<Reclamation> reclamationsNonTratitees();

    @Query("select r from Reclamation r where r.etat=?1")
    List<Reclamation> findReclamationByEtat();
}


