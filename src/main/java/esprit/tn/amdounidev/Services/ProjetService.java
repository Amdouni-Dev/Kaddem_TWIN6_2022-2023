package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ProjetRepository;
import esprit.tn.amdounidev.Repository.TacheRepository;
import esprit.tn.amdounidev.entities.Departement;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;
import esprit.tn.amdounidev.entities.Universite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ProjetService implements IProjetService {

    @Autowired //ou @Inject
    ProjetRepository pr;
    @Autowired
    TacheRepository tr;
    @Override
    public Projet addProjet(Projet p) {

        log.info("Ajout d'un projet");
        return  pr.save(p);
    }

    @Override
    public List<Projet> addProjet(List<Projet> listProjet) {

        log.info("Ajout d'une liste de projets");
        return pr.saveAll(listProjet);
    }

    @Override
    public Projet updateProjet(Projet p , Long id) {

        Projet p2=pr.findByIdProjet(id);

            p2.setNomProjet(p.getNomProjet());
           p2.setDureeProjet(p.getDureeProjet());
           p2.setDateDebutP(p.getDateDebutP());
           p2.setDateFinP(p.getDateFinP());

          p2.setTypeProjet(p.getTypeProjet());





        log.info("modification d'un projet");
        return pr.save(p2);
    }

    @Override
    public List<Projet> updateProjet(List<Projet> listProjet) {

        log.info("modification de liste de projets");
        return pr.saveAll(listProjet);
    }

    @Override
    public void deleteProjet(Long id) {

        log.info("suppression d'un projet par id");
        pr.deleteById(id);
    }

    @Override
    public void deleteProjet(Projet p) {

        log.info("suppression d'un projet");
        pr.delete(p);
    }

    @Override
    public List<Projet> findAllProjet() {
        log.info("récuperation de tous les projets");
        return pr.findAll();
    }

    @Override
    public Projet findProjetById(Long id) {

        log.info("récuperation d'un projet par id");
        return pr.findById(id).orElse(new Projet());
    }

    @Override
    public void aassignProjetToTache(Long idProjet, Long idTache) {
        Projet p= pr.findByIdProjet(idProjet);
        Tache t=tr.findByIdTache(idTache);
        p.getTaches().add(t);
       pr.save(p);

    }

    @Override
    public List<Tache> getTachesByProjet(Long idProjet) {



        return  pr.findByTachesByProjets(idProjet);
    }

    @Override
    public int findByTypePIDEVProjet() {
        return pr.findByTypePIDEVProjet();
    }


    @Override
    public int findByTypePFEProjet() {
        return pr.findByTypePFEProjet();
    }



    @Override
    public int findByTypeJEUVIDEOProjet() {
        return pr.findByTypeJEUVIDEOProjet();
    }





}
