package esprit.tn.amdounidev.Schedular;

import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Repository.EtudiantRepository;
import esprit.tn.amdounidev.Services.EquipeServiceImpl;
import esprit.tn.amdounidev.Services.IContratService;
import esprit.tn.amdounidev.Services.IProjetService;
import esprit.tn.amdounidev.entities.Etudiant;
import esprit.tn.amdounidev.entities.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
public class schedulars {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    IContratService contratService;
    @Autowired
    IProjetService ps;
    @Autowired
    EquipeRepository equipeRepository;
   @Scheduled(cron = "* 1 * * * * ")
    public void selectAll(){
        List<Etudiant> list=(List<Etudiant>) etudiantRepository.findAll();
        for(Etudiant e:list){
            System.out.println(e.toString());
        }
    }

    /*@Scheduled(fixedRate = 30000)
    public void select(){
        System.out.println(contratService.retrieveAndUpdateStatusContrat());
    }
*/





    //@Scheduled(cron = " * * 9 * * *") //tous les jours à 9h
   /* @Scheduled(fixedRate = 60000)
    public void SuppAutomatique() {


        ps.deleteAuto();
        System.out.println("Dans la recherche d un projet a supprimer");
    }
*/
  /*  @Scheduled(cron = " * * * * * *") //dima
    public void DisplayEquipeNonActivées() {


        int nbEquipesActives=equipeRepository.nbEquipesActives();
        int nbNonActivees=equipeRepository.nbEquipesDesactives();
        log.info("Hi Mannou! on vous souhaite une bonne journée Sinon Vous avez "+nbNonActivees+" equipes non activées(Donc Merci de les activées) "+nbEquipesActives+ " equipes activées.");
        System.out.println("Hi Mannou! on vous souhaite une bonne journée Sinon Vous avez "+nbNonActivees+" equipes non activées(Donc Merci de les activées) "+nbEquipesActives+ " equipes activées.");


    } */


}
