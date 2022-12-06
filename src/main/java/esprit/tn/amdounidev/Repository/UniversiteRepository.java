package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite,Long> {

    //Jpql Base sur les nom des attribus dans l'entite  et non pas dasn la base
    @Query("select u from Universite u where u.surfaceUniversite <= ?1")
    List<Universite> RecupereBysurface_universite(int surface_universite);

    //SQL Native
    @Query(value = "select * from Universite where nom_universite = ?1",nativeQuery = true)
    List<Universite> RecupbynomUniversite(String nomUniversite);

//Spring Data JPA - Keywords
    List<Universite> findByetatUniversite(String etatUniversite);



}
