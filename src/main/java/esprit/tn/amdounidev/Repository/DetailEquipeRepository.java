package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.DetailEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailEquipeRepository extends JpaRepository<DetailEquipe,Long> {
    @Query("select detail from DetailEquipe detail where substring(detail.dateCreation,0,4)= '2022' ")
    DetailEquipe nbEquipeCetteHeure();
}
