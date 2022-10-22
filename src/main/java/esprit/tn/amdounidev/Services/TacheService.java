package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.TacheRepository;
import esprit.tn.amdounidev.entities.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService implements  ITacheService {

    @Autowired //ou @Inject
    TacheRepository ts;
    @Override
    public Tache addTache(Tache t) {
        return  ts.save(t);
    }

    @Override
    public List<Tache> addTache(List<Tache> listTache) {
        return ts.saveAll(listTache);
    }

    @Override
    public Tache updateTache(Tache t) {
        return ts.save(t);
    }

    @Override
    public List<Tache> updateTache(List<Tache> listTache) {
        return ts.saveAll(listTache);
    }

    @Override
    public void deleteTache(Long id) {
        ts.deleteById(id);
    }

    @Override
    public void deleteTache(Tache t) {
        ts.delete(t);
    }

    @Override
    public List<Tache> findAllTache() {
        return ts.findAll();
    }

    @Override
    public Tache findTacheById(Long id) {
        return ts.findById(id).orElse(new Tache());
    }
    
    
}
