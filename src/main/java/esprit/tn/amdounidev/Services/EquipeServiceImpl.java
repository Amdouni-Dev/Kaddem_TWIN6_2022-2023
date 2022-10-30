package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Exceptions.EquipeNotFoundException;
import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class EquipeServiceImpl implements EquipeService {
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    DetailEquipeRepository detailEquipeRepository ;


    @Override
    public void saveEquipe(Equipe equipe) {
       DetailEquipe detailEquipe=new DetailEquipe();

       detailEquipe.setDateCreation(new Date());
       detailEquipe.setDateActivation(new Date());
        detailEquipe.setDateSuppression(new Date());
       detailEquipeRepository.save(detailEquipe);
        equipe.setDetailEquipe(detailEquipe);
       equipe.setIsValid(false);
       equipe.setIsDeleted(false);

 equipeRepository.save(equipe);
 log.info("Equipe ajouté :\n" +
         "Nom d\'equipe "+equipe.getNomEquipe() +" " +
         "Niveau " +equipe.getNiveau()+" "+
         "Etat de suppression : non supprimé" +
         "Etat de validation : non validé " +
         "Etat Ajouté par defaut(false)" +
         "Niveau par defaut junior ! ." );
    }

    @Override
    public void saveEquipeAndDetail(Equipe equipe, DetailEquipe detailEquipe) {


        detailEquipe.setDateCreation(new Date());
        detailEquipe.setDateActivation(new Date());
        detailEquipe.setDateSuppression(new Date());
        detailEquipeRepository.save(detailEquipe);
        equipe.setDetailEquipe(detailEquipe);
        equipe.setIsValid(false);
        equipe.setIsDeleted(false);

        equipeRepository.save(equipe);
    }


    @Override
    public Equipe updateEquipe(Equipe equipe, Long idE) {
      //  Equipe updateEquipe = equipeRepository.findById(idE).orElseThrow(()-> new IllegalArgumentException("Not Found"));
        Optional<Equipe> updateEquipe = equipeRepository.findById(idE);
        if (updateEquipe.isPresent()) {
        equipe.setNomEquipe  (equipe.getNomEquipe());
            equipe.setNiveau(equipe.getNiveau());
            equipe.setIsValid(false);
            equipe.setIsDeleted(false);



        equipeRepository.save(equipe);}
        return equipe;
    }











    @Override
    public void deleteEquipe(Equipe equipe) {
        equipeRepository.delete(equipe);

    }
    

    @Override
    public void deleteEquipeById(Long id) {
        equipeRepository.deleteById(id);
    }

    @Override
    public List<Equipe> findEquipes() {



        return equipeRepository.findAllEquipes();
    }

    @Override
    public Equipe findById(Long id) {
        return equipeRepository.findById(id).orElseThrow(() -> new EquipeNotFoundException(id));
    }

    @Override
    public Boolean isValid(Equipe e,Long id) {
        if( e.getIdEquipe()==id && e.getIsValid()==true){
            return true;
        }
        else return false;
    }

    @Override
    public Boolean changeIsValid(Equipe e) {



        if(e.getIsValid()==true){
            e.setIsValid(false);
            return true;
        }
        else e.setIsValid(true);
        return false;
    }


    public Equipe addE(Equipe e) {
        return equipeRepository.save(e);
    }


}
