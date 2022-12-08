package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ReclamationRepository;
import esprit.tn.amdounidev.entities.Reclamation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

      return reclamationRepository.save(reclamation);

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
    public Page<Reclamation> listeReclamations(Pageable pageable) {
        return reclamationRepository.findAll(pageable);
    }

    @Override
    public List<Reclamation> listeReclamationsNonTratitees() {
        return reclamationRepository.reclamationsNonTratitees();
    }

    @Override
    public List<Reclamation> listeReclamationsTratitees() {
        return reclamationRepository.reclamationsTraitees();
    }

    @Override
    public Reclamation traiterRec(Long id, Reclamation reclamation) {
        reclamation=this.reclamationRepository.findById(id).get();
        reclamation.setEtat(true);
        return reclamation;
    }

    @Override
    public Reclamation findReclamationById(Long id) {
        return reclamationRepository.findById(id).orElse(new Reclamation());
    }
}
