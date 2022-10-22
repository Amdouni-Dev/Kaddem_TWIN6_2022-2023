package esprit.tn.amdounidev.Repository;


import esprit.tn.amdounidev.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {
}
