package esprit.tn.amdounidev.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import esprit.tn.amdounidev.Repository.ProjetRepository;
import esprit.tn.amdounidev.Repository.TacheRepository;
import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;
import esprit.tn.amdounidev.entities.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class ProjetService implements IProjetService {

    @Autowired //ou @Inject
    ProjetRepository pr;
    @Autowired
    TacheRepository tr;

   public static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

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
    public List<Projet> findAllProjet(int pageNo, int pageSize) {
        log.info("récuperation de tous les projets");
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Projet> pagedResult = pr.findAll(paging);

        return pagedResult.toList();




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

    @Override
    public Projet findProjectByName(String nom){

      return pr.findProjectByName(nom);
    }
    @Override
    public Projet getProjetPerime(){
        Date date=new Date();

     return pr.getProjetPerime(date)   ;
    }
    @Override
    public int deleteAuto() {
        Date date=new Date();
        if(getProjetPerime()!=null) {
            Projet p = getProjetPerime();
            String nom = p.getNomProjet();
            Type type = p.getTypeProjet();

            if (pr.deleteAutomatique(date) != 0) {


                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                Message message = Message.creator(new PhoneNumber("+21695218588"),
                        new PhoneNumber("+13853964540"),
                        "Le projet " + nom + " de type " + type + " a été  supprimé ainsi que ses taches ").create();

                System.out.println(message.getSid());
                System.out.println("Un projet a ete supprime avec succes el sms twilio vous sera envoye");

                return 1;

            } else {
                System.out.println("pas de projet à supprimer");
                return -1;
            }
        }else
            return -1;

    }
    @Override
    public Projet retrieveProjet(Long id){

        return pr.findById(id).get();
    }
}
