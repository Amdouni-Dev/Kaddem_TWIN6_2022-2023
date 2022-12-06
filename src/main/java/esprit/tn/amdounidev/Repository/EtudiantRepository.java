package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
/*SELECT *
FROM `table`
WHERE `nom_colonne` IN (
    SELECT `colonne`
    FROM `table2`
    WHERE `cle_etrangere` = 36
  )*/
    @Query(value = "select  * from etudiant e, departement d where" +
            " e.departement_id_departement.id_departement = d.id_departement" +
            " and d in (select u.departments_id_departement" +
            " from universite_departments u where u.nomUniversite=?1) " +
            " and d.nomDepartement=?2  ",nativeQuery = true)
    public List<Etudiant> findAllBy(String universite, String departement);

}
