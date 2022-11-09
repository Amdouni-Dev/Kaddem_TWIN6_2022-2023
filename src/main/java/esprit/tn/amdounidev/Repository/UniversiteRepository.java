package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UniversiteRepository extends JpaRepository<Universite,Long> {

    //Jpql Base sur les nom des attribus dans l'entite  et non pas dasn la base
    //select * from Universite where nomUniversite=?
    Universite findBynomUniversite(String nomUniversite);


/*
    List<Universite> getBydateFinC(Date dateFinC);
    //select * from Contrat where nom=? and prenom
//zdvccccccccccc
    List<Universite> findByspecialite(String specialite);
    List<Universite> findByMontantCBetween(float min,float Max);
 */
}
