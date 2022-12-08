package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.DepartementRepository;
import esprit.tn.amdounidev.Repository.UniversiteRepository;
import esprit.tn.amdounidev.entities.Departement;
import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Universite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UniversiteService implements IUniversiteService {
    @Autowired //ou @Inject
    UniversiteRepository UR;

    @Autowired
    DepartementRepository DR;

    @Override
    public Universite addUniversite(Universite universite) {
        log.info("universite ajouté :\n" +
                "Nom d\'universite "+universite.getNomUniversite() +" " +
                "ID d\'universite " +universite.getIdUniversite()+"" );

        universite.setDate_creation(LocalDateTime.now());
        return UR.save(universite);
    }

    @Override
    public List<Universite> addUniversite(List<Universite> listUniversite) {
        log.info("Une Liste d'Universites a ete Ajouter");
        return UR.saveAll(listUniversite);
    }

    @Override
    public Universite updateUniversite(Universite universite, long id) {


        //  Equipe updateEquipe = equipeRepository.findById(idE).orElseThrow(()-> new IllegalArgumentException("Not Found"));
        Universite universite1 = UR.findById(id).get();

            universite1.setNomUniversite  (universite.getNomUniversite());
            universite1.setEtatUniversite(universite.getEtatUniversite());
            universite1.setReputationUniversite(universite.getReputationUniversite());
            universite1.setSurfaceUniversite(universite.getSurfaceUniversite());
        universite1.setDate_creation(LocalDateTime.now());
        universite1.setDate_update(LocalDateTime.now());


        UR.save(universite1);
        return universite;
        }

    @Override
    public List<Universite> updateUniversite(List<Universite> listUniversite) {
        log.info("modification de Une Liste d'Universites");
        return UR.saveAll(listUniversite);
    }

    @Override
    public void deleteUniversite(Long id) {
        log.info("suppression d'Une Universite par id");
        UR.deleteById(id);
    }

    @Override
    public void deleteUniversite(Universite d) {
        log.info("suppression d'Une Universite specifique");
        UR.delete(d);
    }

    @Override
    public List<Universite> findAllUniversite() {
        log.info("récuperation de tous les Universites");
        return UR.findAll();
    }

    @Override
    public List<Universite> RecupbynomUniversite(String Nom) {
        log.info("récuperation d'un Universites par Nom");
        return UR.RecupbynomUniversite(Nom);
    }

    @Override
    public List<Universite> RecupereBysurface_universite(int surface_universite) {
        log.info("récuperation d'un Universites par surface_universite");
        return UR.RecupereBysurface_universite(surface_universite);
    }

    @Override
    public Universite findUniversiteById(Long id) {
        log.info("récuperation d'un Universites par ID");
        return UR.findById(id).orElse(new Universite());
    }


    //relation one to many bidire ctionelle
    @Override
    public void assignUniversitetoDepartement(Long idUniversite, Long idDepartement) {
        log.info("Affecter a une Universite un Departement par son ID");
        Universite u= UR.findById((long)idUniversite).get(); //Parent
        Departement d=DR.findById((long)idDepartement).get(); //child
        u.setDepartments((Set<Departement>) d);
        UR.save(u);
    }

    @Override
    public void desaffectUniversitetoDepartement(Long idUniversite, Long idDepartement) {
        log.info("desAffecter a une Universite un Departement");
        Universite u= UR.findById((long)idUniversite).get(); //Parent
        Departement d=DR.findById((long)idDepartement).get(); //child
        u.getDepartments().remove(d);
        UR.save(u);
    }

    //relation one to many unidirectionelle
    @Override
    public void aassignUniversitetoDepartement(Long idUniversite, Long idDepartement) {
        log.info("Affecter a une Universite un Departement relation one to many unidirectionelle");
        Universite u= UR.findById((long)idUniversite).get(); //Parent
        Departement d=DR.findById((long)idDepartement).get(); //child
        //on affect le child ou master
        u.getDepartments().add(d);
        UR.save(u);
    }

    @Override
    public void assignUniversitetolistDepartement(Long idUniversite, List<Long> ListidDepartement) {
        log.info("Affecter a une Universite une liste des Departements ");
        Universite u= UR.findById((long)idUniversite).get(); //Parent
        for (long i: ListidDepartement )
        {
            Departement d=DR.findById((long)i).get();
            u.getDepartments().add(d);
        }
        UR.save(u);
    }



}
