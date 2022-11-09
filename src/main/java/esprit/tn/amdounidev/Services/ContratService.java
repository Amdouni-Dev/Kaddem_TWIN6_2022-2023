package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ContratRepository;
import esprit.tn.amdounidev.entities.Contrat;
import esprit.tn.amdounidev.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContratService implements IContratService{

    // @Inject
    @Autowired
    ContratRepository contratRepository;

    @Override
    public Contrat saveContart(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat updateContart(Contrat contrat, Long id) {
        Optional<Contrat> updateContrat = contratRepository.findById(id);
        if(updateContrat.isPresent()){
            Long idCtrt = contrat.getIdContrat();
            System.out.println(idCtrt);
            contrat.setArchive(contrat.getArchive());
            contrat.setDateDebutC(new Date());
            contrat.setDateFinC(new Date());
            contrat.setMontantC(contrat.getMontantC());
            contrat.setSpecailite(contrat.getSpecailite());
            contratRepository.save(contrat);
        }
        return contrat;
    }

    @Override
    public void deleteContratById(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public void deleteContrat(Contrat contrat) {
        contratRepository.delete(contrat);
    }

    @Override
    public List<Contrat> listeContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat findContratById(Long id) {
        return contratRepository.findById(id).orElse(new Contrat());
    }

    @Override
    public Etudiant AddandassigntEtudianttoequipeandcontract(Etudiant e, Integer IDContrat, Integer idEquipe) {
        return null;
    }
}
