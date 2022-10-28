package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread,Long> {
}
