package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Equipe;

import java.util.List;
import java.util.Optional;

public interface EquipeService {

    Equipe saveEquipe (Equipe equipe);


    Equipe updateEquipe (Equipe equipe,Long idE);
    void deleteEquipe (Equipe equipe);
    void deleteEquipeById (Long id);
    List<Equipe> findEquipes();
    Optional<Equipe> findById(Long id) ;

    Boolean isValid(Equipe e,Long id);
    Boolean changeIsValid(Equipe e);

}
