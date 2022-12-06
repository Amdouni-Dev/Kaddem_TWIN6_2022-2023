package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Etudiant;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

    @Query(value="SELECT * FROM etudiant e join departement d  on e.departement_id_departement=d.id_departement join universite_departments u " +
            " on d.id_departement=u.departments_id_departement join universite a on a.id_universite=u.universite_id_universite  " +
            " where d.nom_departement like 'Informatique' and  a.nom_universite like 'Esprit' ",nativeQuery = true)


    public List<Etudiant> findE();




    @Query("SELECT e FROM Etudiant e WHERE e.idE = ?1")
    public Etudiant findByIdEtudiant(Long id);

}
