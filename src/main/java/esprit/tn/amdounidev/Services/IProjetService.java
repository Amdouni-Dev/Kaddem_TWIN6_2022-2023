package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IProjetService {

    Projet addProjet(Projet d);
    List<Projet> addProjet(List<Projet> listProjet);

    Projet updateProjet(Projet d,Long id);
    List<Projet> updateProjet(List<Projet> listProjet);

    void deleteProjet(Long id);
    void deleteProjet(Projet d);

    List<Projet> findAllProjet(int pageNo, int pageSize);
    Projet findProjetById(Long id);
    public void aassignProjetToTache(Long idProjet, Long idTache);

    public List<Tache> getTachesByProjet(Long idProjet);


    public int findByTypePIDEVProjet();
    public int findByTypePFEProjet();
    public int findByTypeJEUVIDEOProjet();

    public Projet findProjectByName(String nom);
    public int deleteAuto();
    public Projet getProjetPerime();

    public Projet retrieveProjet(Long id);
}
