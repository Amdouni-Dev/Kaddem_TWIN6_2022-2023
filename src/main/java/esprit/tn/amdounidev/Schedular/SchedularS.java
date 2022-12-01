package esprit.tn.amdounidev.Schedular;

import esprit.tn.amdounidev.Repository.ContratRepository;
import esprit.tn.amdounidev.Repository.ProjetRepository;
import esprit.tn.amdounidev.Services.IProjetService;
import esprit.tn.amdounidev.controllers.ProjetController;
import esprit.tn.amdounidev.entities.Contrat;
import esprit.tn.amdounidev.entities.Departement;
import esprit.tn.amdounidev.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedularS {
@Autowired
   ProjetRepository ps;
    ContratRepository cr;

   /* @Scheduled(fixedRate = 60000)
    public void fixedRateMethod() {


                System.out.println("Method with fixed Rate");

    }

    */



}
