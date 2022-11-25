package esprit.tn.amdounidev.Schedular;

import esprit.tn.amdounidev.Repository.EtudiantRepository;
import esprit.tn.amdounidev.Services.IContratService;
import esprit.tn.amdounidev.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class schedulars {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    IContratService contratService;

    @Scheduled(cron = "* 1 * * * * ")
    public void selectAll(){
        List<Etudiant> list=(List<Etudiant>) etudiantRepository.findAll();
        for(Etudiant e:list){
            System.out.println(e.toString());
        }
    }

    @Scheduled(cron = "0 0 13 * * *")
    public void select(){
        System.out.println(contratService.retrieveAndUpdateStatusContrat());
    }
}
