package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Contrat;
import esprit.tn.amdounidev.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IContratService {

    Page<Contrat> retrieveAllContrats(Pageable pageable);
    Contrat updateContart(Long id,Contrat contrat);
    Contrat addContart(Contrat ce);
    Contrat retrieveContrat (Long idContrat);
    void removeContratById(Long idContrat);
    void removeContrat(Contrat ce);

    Integer nbContratsValides(Date startDate, Date endDate);

    Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer
            idEquipe);

    Float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate, Long idUniversite);

    Contrat affectContratToEtudiant (Contrat ce, String nomE, String prenomE);

    String retrieveAndUpdateStatusContrat();

    List<Contrat> contratsArchives();
    List<Contrat> contratsNonArchives();
}

