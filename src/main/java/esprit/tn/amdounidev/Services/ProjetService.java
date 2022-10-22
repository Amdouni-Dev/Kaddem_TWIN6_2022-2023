package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ProjetRepository;
import esprit.tn.amdounidev.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetService implements IProjetService {

    @Autowired //ou @Inject
    ProjetRepository pr;
    @Override
    public Projet addProjet(Projet p) {
        return  pr.save(p);
    }

    @Override
    public List<Projet> addProjet(List<Projet> listProjet) {
        return pr.saveAll(listProjet);
    }

    @Override
    public Projet updateProjet(Projet p) {
        return pr.save(p);
    }

    @Override
    public List<Projet> updateProjet(List<Projet> listProjet) {
        return pr.saveAll(listProjet);
    }

    @Override
    public void deleteProjet(Long id) {
        pr.deleteById(id);
    }

    @Override
    public void deleteProjet(Projet p) {
        pr.delete(p);
    }

    @Override
    public List<Projet> findAllProjet() {
        return pr.findAll();
    }

    @Override
    public Projet findProjetById(Long id) {
        return pr.findById(id).orElse(new Projet());
    }
}
