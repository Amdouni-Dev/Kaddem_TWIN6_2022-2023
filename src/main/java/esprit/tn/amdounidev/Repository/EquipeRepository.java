package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface EquipeRepository extends CrudRepository<Equipe,Long> {
// afficher la liste des equipres thematique=sport


    @Query("SELECT e FROM Equipe e WHERE e.nomEquipe = ?1")
    public Equipe findByNom(String nomEquipe);
    @Query("SELECT e FROM Equipe e WHERE e.idEquipe = ?1")
    public Equipe findByIdEquipe(Long nomEquipe);

    @Query("SELECT e.detailEquipe.idDetailEquipe FROM Equipe e ")
    public List<Equipe> findAllEquipes();
    @Query("select  d from Equipe e , DetailEquipe d where e.detailEquipe.idDetailEquipe = d.idDetailEquipe ")
    List<DetailEquipe> equipesAndDetails();
    @Transactional
    @Modifying
    @Query("update Equipe e set e.isValid =:valid  where e.idEquipe =:id")
    void changeValiditeEquipe(@Param("id") Long id, @Param("valid") Boolean valid);

    @Transactional
    @Modifying
    @Query("update Equipe e set e.isDeleted =:deleted  where e.idEquipe =:id")
    void changeDeleteEquipe(@Param("id") Long id, @Param("deleted") Boolean deleted);

















}
