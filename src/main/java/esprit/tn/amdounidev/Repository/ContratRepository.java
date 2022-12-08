package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Contrat;
import esprit.tn.amdounidev.entities.Etudiant;
import esprit.tn.amdounidev.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

    @Query("select c from Contrat c where c.archive=true" )
    public List<Contrat> nbrContrat();

    @Query("select c from Contrat c where c.archive=false")
    List<Contrat> contratsNonArchives();

    @Query("select e from Etudiant e where e.nom=?1 and e.prenom=?1")
    Etudiant EtudiantByNomAndPrenom(String nom, String prenom);



    //List<Contrat> findByMontantCAndSpecailiteAndEtudiantNomAndEtudiantDepartement_NomDepartementAndEtudiantDepartement();

    //    List<Universite> findByNomUniversiteAndDepartments();

}
