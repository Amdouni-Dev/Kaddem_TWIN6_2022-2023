package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Long> {


    @Query("SELECT r FROM Reponse r WHERE r.thread.id = ?1 ")
    public List<Reponse>findByThread(long id);
}
