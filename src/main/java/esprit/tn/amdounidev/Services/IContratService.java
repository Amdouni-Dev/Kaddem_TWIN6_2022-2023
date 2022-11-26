package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Contrat;
import esprit.tn.amdounidev.entities.Etudiant;

import java.util.Date;
import java.util.List;

public interface IContratService {

    List<Contrat> retrieveAllContrats();
    Contrat updateContart(Contrat contrat, Long id);
    Contrat addContart(Contrat ce);
    Contrat retrieveContrat (Long idContrat);
    void removeContrat(Long idContrat);
    void removeContrat(Contrat ce);

    Integer nbContratsValides(Date startDate, Date endDate);

    Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer
            idEquipe);

    public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate);

    Contrat affectContratToEtudiant (Contrat ce, String nomE, String prenomE);

    String retrieveAndUpdateStatusContrat();
}
