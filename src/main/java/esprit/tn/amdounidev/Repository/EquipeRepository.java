package esprit.tn.amdounidev.Repository;

import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Long> {
// afficher la liste des equipres thematique=sport

    @Query("select d from DetailEquipe d, Equipe e where e.detailEquipe.idDetailEquipe=?1 ")
    public DetailEquipe findDetailByIdEquipe(Long idEquipe);
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
    @Query("select  count(etudiant) from Etudiant etudiant where etudiant.roleEtudiantEquipe = 'RESPONSABLE' ")
    int nbResponsableEquipes();
    @Query("select  count(etudiant) from Etudiant etudiant where etudiant.roleEtudiantEquipe ='MEMBRE' ")
    int nbMembresEquipes();
    @Query("select  count(etudiant) from Etudiant etudiant , Equipe  equipe where etudiant.roleEtudiantEquipe ='MEMBRE' and equipe.idEquipe =?1 ")
    int nbMembresParEquipes(Long idEquipe);
    @Query("select  count(etudiant) from Etudiant etudiant , Equipe  equipe where etudiant.roleEtudiantEquipe ='RESPONSABLE' and   equipe.idEquipe =?1 and equipe.etudiant.idE= etudiant.idE ")
    int nbResponsableParEquipes(Long idEquipe);
    @Query("select  count(equipe) from  Equipe  equipe where equipe.isValid <> true   ")
    int nbEquipesDesactives();
    @Query("select  count(equipe) from  Equipe  equipe    ")
    int nbEquipes();
    @Query("select  count(equipe) from  Equipe  equipe where equipe.isValid = true   ")
    int nbEquipesActives();
    @Query("select  et from  Equipe  equipe ,Etudiant  et where et.roleEtudiantEquipe='MEMBRE' and equipe.idEquipe =?1  ")
    Etudiant getMembers(Long id);


    @Query("select  et from  Etudiant  et where  et.roleEtudiantEquipe IS NULL  ")
    List<Etudiant> getEtudiantsNonResponsables();
    //substring( detail.date_creation  ,11,13)=?1
  //  @Query(value="select  count(*) from detail_equipe detail where substring(  detail.date_creation,11,13)=?1 ",nativeQuery = true)

  /**  @Query("delete  from (select Equipe)")
    int RetirerMembreEquipe();
*/















}
