package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Contrat;
import esprit.tn.amdounidev.entities.Etudiant;

import java.util.List;

public interface IContratService {

    Contrat saveContart(Contrat contrat);
    Contrat updateContart(Contrat contrat, Long id);
    void deleteContratById(Long id);
    void deleteContrat(Contrat contrat);
    List<Contrat> listeContrats();
        Contrat findContratById(Long id);

    public Etudiant AddandassigntEtudianttoequipeandcontract(Etudiant e, Integer IDContrat, Integer idEquipe);

}
