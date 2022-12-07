package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Reponse;
import esprit.tn.amdounidev.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends CrudRepository<Thread,Long> {

    @Query("select count(e) from Thread e ")
    long countThreads();
    @Query("select e from Thread e WHERE e.question LIKE %?1 ")
    Thread findlikeTh(String id);
    @Query("select t from Thread t WHERE t.postDate is not  null  ")
    List< Thread> findAll1();
}
