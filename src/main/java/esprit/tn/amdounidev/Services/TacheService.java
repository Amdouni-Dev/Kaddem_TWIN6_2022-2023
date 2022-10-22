package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.TacheRepository;
import esprit.tn.amdounidev.entities.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService implements  ITacheService {

    @Autowired //ou @Inject
    TacheRepository TacheRep;
    @Override
    public Tache addTache(Tache t) {
        return  TacheRep.save(t);
    }

    @Override
    public List<Tache> addTache(List<Tache> listTache) {
        return TacheRep.saveAll(listTache);
    }

    @Override
    public Tache updateTache(Tache t) {
        return TacheRep.save(t);
    }

    @Override
    public List<Tache> updateTache(List<Tache> listTache) {
        return TacheRep.saveAll(listTache);
    }

    @Override
    public void deleteTache(Long id) {
        TacheRep.deleteById(id);
    }

    @Override
    public void deleteTache(Tache t) {
        TacheRep.delete(t);
    }

    @Override
    public List<Tache> findAllTache() {
        return TacheRep.findAll();
    }

    @Override
    public Tache findTacheById(Long id) {
        return TacheRep.findById(id).orElse(new Tache());
    }
    
    
}
