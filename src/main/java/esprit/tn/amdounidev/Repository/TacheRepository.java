package esprit.tn.amdounidev.Repository;


import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {

}
