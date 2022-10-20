package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.entities.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeServiceImpl implements EquipeService {
    @Autowired
    EquipeRepository equipeRepository;


    @Override
    public Equipe saveEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public Equipe updateEquipe(Equipe equipe, Long idE) {
        return null;
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


}
