package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ReclamationRepository;
import esprit.tn.amdounidev.entities.Reclamation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReclamationService implements IReclamationService{

    @Autowired
    ReclamationRepository reclamationRepository;

    @Override
    public Reclamation saveReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> saveReclamations(List<Reclamation> listReclamations) {
        return reclamationRepository.saveAll(listReclamations);
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation, Long id) {
        Optional<Reclamation> updateRec = reclamationRepository.findById(id);
        if(updateRec.isPresent()){
            Long idRec= reclamation.getId();
            System.out.println(idRec);
            reclamation.setDate(new Date());
            reclamation.setMessage(reclamation.getMessage());
            reclamation.setTitle(reclamation.getTitle());
            reclamation.setImage(reclamation.getImage());
            reclamation.setEtat(reclamation.getEtat());
            reclamationRepository.save(reclamation);
        }
        return reclamation;
    }

    @Override
    public List<Reclamation> updateReclamations(List<Reclamation> listReclamations) {
        return reclamationRepository.saveAll(listReclamations);
    }

    @Override
    public void deleteReclamationById(Long id) {
        reclamationRepository.deleteById(id);
    }

    @Override
    public void deleteReclamation(Reclamation reclamation) {
        reclamationRepository.delete(reclamation);
    }

    @Override
    public List<Reclamation> listeReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation findReclamationById(Long id) {
        return reclamationRepository.findById(id).orElse(new Reclamation());
    }
}
