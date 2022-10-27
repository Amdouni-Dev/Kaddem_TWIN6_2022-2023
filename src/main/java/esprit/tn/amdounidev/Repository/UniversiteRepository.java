package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UniversiteRepository extends JpaRepository<Universite,Long> {

    //select * from Universite where nomUniversite=?
    List<Universite> findBynomUniversite(String nomUniversite);

/*
    List<Universite> getBydateFinC(Date dateFinC);
    //select * from Contrat where nom=? and prenom
//zdvccccccccccc
    List<Universite> findByspecialite(String specialite);
    List<Universite> findByMontantCBetween(float min,float Max);
 */
}
