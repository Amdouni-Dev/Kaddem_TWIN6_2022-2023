package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.DetailEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailEquipeRepository extends JpaRepository<DetailEquipe,Long> {
}
