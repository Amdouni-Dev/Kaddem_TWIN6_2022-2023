package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EquipeService {

    void saveEquipe (Equipe equipe);


    Equipe updateEquipe (Equipe equipe,Long idE);
    void deleteEquipe (Equipe equipe);
    void deleteEquipeById (Long id);
    List<Equipe> findEquipes();
    Optional<Equipe> findById(Long id) ;

    Boolean isValid(Equipe e,Long id);
    Boolean changeIsValid(Equipe e);

}
