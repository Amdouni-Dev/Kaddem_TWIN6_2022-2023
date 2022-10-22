package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.UniversiteRepository;
import esprit.tn.amdounidev.entities.Universite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversiteService implements IUniversiteService{
    @Autowired //ou @Inject
    UniversiteRepository dr;
    @Override
    public Universite addUniversite(Universite d) {
        return  dr.save(d);
    }

    @Override
    public List<Universite> addUniversite(List<Universite> listUniversite) {
        return dr.saveAll(listUniversite);
    }

    @Override
    public Universite updateUniversite(Universite d) {
        return dr.save(d);
    }

    @Override
    public List<Universite> updateUniversite(List<Universite> listUniversite) {
        return dr.saveAll(listUniversite);
    }

    @Override
    public void deleteUniversite(Long id) {
        dr.deleteById(id);
    }

    @Override
    public void deleteUniversite(Universite d) {
        dr.delete(d);
    }

    @Override
    public List<Universite> findAllUniversite() {
        return dr.findAll();
    }

    @Override
    public Universite findUniversiteById(Long id) {
        return dr.findById(id).orElse(new Universite());
    }
}
