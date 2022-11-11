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
public class TacheService implements  ITacheService {

    @Autowired //ou @Inject
    TacheRepository ts;

    @Autowired //ou @Inject
    ProjetRepository pr;

    @Override
    public Tache addTache(Tache t) {

        log.info("Ajout d'une tache");
        return  ts.save(t);
    }

    @Override
    public List<Tache> addTache(List<Tache> listTache) {

        log.info("Ajout d'une liste de taches");
        return ts.saveAll(listTache);
    }

    @Override
    public Tache updateTache(Tache t) {

        log.info("modification d'une tache");
        return ts.save(t);
    }

    @Override
    public List<Tache> updateTache(List<Tache> listTache) {

        log.info("modification de liste de taches");
        return ts.saveAll(listTache);
    }

    @Override
    public void deleteTache(Long id) {
        log.info("suppression d'une tache par id");
        ts.deleteById(id);
    }

    @Override
    public void deleteTache(Tache t) {

        log.info("suppression d'une tache");
        ts.delete(t);
    }

    @Override
    public List<Tache> findAllTache() {
        log.info("récuperation de toutes les taches");
        return ts.findAll();
    }

    @Override
    public Tache findTacheById(Long id) {
        log.info("récuperation d'une tache par id");
        return ts.findById(id).orElse(new Tache());
    }

    @Override
    public void aassignProjetToTache(Long idProjet, Long idTache) {
        Projet p= pr.findByIdProjet(idProjet);
        Tache t=ts.findByIdTache(idTache);
        t.setProjet(p);

        ts.save(t);

    }


    @Override
    public void assignProjetToListTache(Long idProjet, List<Long> ListIdTaches) {
        Projet p= pr.findByIdProjet(idProjet);
        for (Long i: ListIdTaches )
        {
            Tache t=ts.findByIdTache(i);
            p.getTaches().add(t);
        }
        pr.save(p);
    }



    @Override
    public Projet getProjetByTache(Long idTache) {
        Tache t=ts.findByIdTache(idTache);
        return t.getProjet();
    }


}
