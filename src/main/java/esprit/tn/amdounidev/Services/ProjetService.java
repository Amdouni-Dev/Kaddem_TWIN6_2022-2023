package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ProjetRepository;
import esprit.tn.amdounidev.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetService implements IProjetService {

    @Autowired //ou @Inject
    ProjetRepository projetRep;
    @Override
    public Projet addProjet(Projet p) {
        return  projetRep.save(p);
    }

    @Override
    public List<Projet> addProjet(List<Projet> listProjet) {
        return projetRep.saveAll(listProjet);
    }

    @Override
    public Projet updateProjet(Projet p) {
        return projetRep.save(p);
    }

    @Override
    public List<Projet> updateProjet(List<Projet> listProjet) {
        return projetRep.saveAll(listProjet);
    }

    @Override
    public void deleteProjet(Long id) {
        projetRep.deleteById(id);
    }

    @Override
    public void deleteProjet(Projet p) {
        projetRep.delete(p);
    }

    @Override
    public List<Projet> findAllProjet() {
        return projetRep.findAll();
    }

    @Override
    public Projet findProjetById(Long id) {
        return projetRep.findById(id).orElse(new Projet());
    }
}
