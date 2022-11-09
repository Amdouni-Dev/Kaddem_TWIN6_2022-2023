package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.DepartementRepository;
import esprit.tn.amdounidev.Repository.UniversiteRepository;
import esprit.tn.amdounidev.entities.Departement;
import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Universite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UniversiteService implements IUniversiteService {
    @Autowired //ou @Inject
    UniversiteRepository UR;

    @Autowired
    DepartementRepository DR;

    @Override
    public Universite addUniversite(Universite d) {
        return UR.save(d);
    }

    @Override
    public List<Universite> addUniversite(List<Universite> listUniversite) {
        return UR.saveAll(listUniversite);
    }

    @Override
    public Universite updateUniversite(Universite universite, long id) {
        Optional<Universite> updateUniversite = UR.findById(id);
        if (updateUniversite.isPresent()) {
            long i = universite.getIdUniversite();
            System.out.println("sasa"+i);

            universite.setIdUniversite(id);
            universite.setNomUniversite(universite.getNomUniversite());
            return UR.save(universite);
        }
        return null;
    }

    @Override
    public List<Universite> updateUniversite(List<Universite> listUniversite) {
        return UR.saveAll(listUniversite);
    }

    @Override
    public void deleteUniversite(Long id) {
        UR.deleteById(id);
    }

    @Override
    public void deleteUniversite(Universite d) {
        UR.delete(d);
    }

    @Override
    public List<Universite> findAllUniversite() {
        return UR.findAll();
    }

    @Override
    public Universite findBynomUniversite(String Nom) {
        return UR.findBynomUniversite(Nom);
    }

    @Override
    public Universite findUniversiteById(Long id) {
        return UR.findById(id).orElse(new Universite());
    }


    //relation one to many bidirectionelle
    @Override
    public void assignUniversitetoDepartement(Integer idUniversite, Integer idDepartement) {
        Universite u= UR.findById((long)idUniversite).get(); //Parent
        Departement d=DR.findById((long)idDepartement).get(); //child
        u.setDepartments((Set<Departement>) d);
        UR.save(u);
    }

    @Override
    public void desaffectUniversitetoDepartement(Integer idUniversite, Integer idDepartement) {
        Universite u= UR.findById((long)idUniversite).get(); //Parent
        Departement d=DR.findById((long)idDepartement).get(); //child
        u.getDepartments().remove(d);
        UR.save(u);
    }

    //relation one to many unidirectionelle
    @Override
    public void aassignUniversitetoDepartement(Integer idUniversite, Integer idDepartement) {
        Universite u= UR.findById((long)idUniversite).get(); //Parent
        Departement d=DR.findById((long)idDepartement).get(); //child
        //on affect le child ou master
        u.getDepartments().add(d);
        UR.save(u);
    }

    @Override
    public void assignUniversitetolistDepartement(Integer idUniversite, List<Integer> ListidDepartement) {
        Universite u= UR.findById((long)idUniversite).get(); //Parent
        for (Integer i: ListidDepartement )
        {
            Departement d=DR.findById((long)i).get();
            u.getDepartments().add(d);
        }
        UR.save(u);
    }



}
