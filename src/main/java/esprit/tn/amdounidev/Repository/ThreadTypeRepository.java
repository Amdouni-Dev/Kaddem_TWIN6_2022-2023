package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Thread;
import esprit.tn.amdounidev.entities.ThreadType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadTypeRepository extends JpaRepository<ThreadType,Long> {
}
