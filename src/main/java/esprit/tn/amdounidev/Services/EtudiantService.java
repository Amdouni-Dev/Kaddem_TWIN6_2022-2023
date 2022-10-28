package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.EtudiantRepository;
import esprit.tn.amdounidev.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EtudiantService implements IEtudiantService{
    @Autowired //ou @Inject
    EtudiantRepository dr;
    @Override
    public Etudiant addEtudiant(Etudiant d) {
        return  dr.save(d);
    }

    @Override
    public List<Etudiant> addEtudiant(List<Etudiant> listEtudiant) {
        return dr.saveAll(listEtudiant);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant d) {
        return dr.save(d);
    }

    @Override
    public List<Etudiant> updateEtudiant(List<Etudiant> listEtudiant) {
        return dr.saveAll(listEtudiant);
    }

    @Override
    public void deleteEtudiant(Long id) {
        dr.deleteById(id);
    }

    @Override
    public void deleteEtudiant(Etudiant d) {
        dr.delete(d);
    }

    @Override
    public List<Etudiant> findAllEtudiant() {
        return dr.findAll();
    }

    @Override
    public Etudiant findEtudiantById(Long id) {
        return dr.findById(id).orElse(new Etudiant());
    }
}
