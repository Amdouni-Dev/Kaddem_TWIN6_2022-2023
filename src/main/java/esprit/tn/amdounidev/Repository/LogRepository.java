package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends CrudRepository<Log,Integer> {



}
