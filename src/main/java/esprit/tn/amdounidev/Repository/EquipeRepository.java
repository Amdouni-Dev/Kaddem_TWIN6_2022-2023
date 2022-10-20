package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Long> {


    @Query("SELECT e FROM Equipe e WHERE e.nomEquipe = ?1")
    public Equipe findByNom(String nomEquipe);
    @Query("SELECT e FROM Equipe e ")
    public List<Equipe> findAllEquipes();


    @Transactional
    @Modifying
    @Query("update Equipe e set e.isValid =:valid  where e.idEquipe =:id")
    void changeValiditeEquipe(@Param("id") Long id, @Param("valid") Boolean valid);

    @Transactional
    @Modifying
    @Query("update Equipe e set e.isDeleted =:deleted  where e.idEquipe =:id")
    void changeDeleteEquipe(@Param("id") Long id, @Param("deleted") Boolean deleted);

















}
