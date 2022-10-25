package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
 equipeRepository.save(equipe);
    }


    @Override
    public Equipe updateEquipe(Equipe equipe, Long idE) {
        Equipe updateEquipe = equipeRepository.findById(idE).orElseThrow();


        updateEquipe.setNomEquipe  (equipe.getNomEquipe());
        updateEquipe.setNiveau(equipe.getNiveau());
        updateEquipe.setIsValid(false);
        updateEquipe.setIsDeleted(false);



        equipeRepository.save(updateEquipe);
        return updateEquipe;
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
    public Optional<Equipe> findById(Long id) {
        return equipeRepository.findById(id);
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
