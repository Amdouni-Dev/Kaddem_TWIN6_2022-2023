package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Services.EquipeService;
import esprit.tn.amdounidev.Services.EquipeServiceImpl;
import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Niveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/")
@RestController
public class EquipeController {

    @Autowired
    EquipeServiceImpl equipeService;
    @Autowired
    EquipeRepository repo;
    @Autowired
    DetailEquipeRepository detailEquipeRepository;

    @GetMapping("/equipes")
    public List<Equipe> findEquipeList(){
         return (List<Equipe>) repo.findAll();
     }

@GetMapping("/equipesAndDetails")
public  List<DetailEquipe> equipeAndDetails(){
        return repo.equipesAndDetails();
}

     @PostMapping("/ChangeValiditeEquipe/{id}")
     public Boolean ChangeIsValid(Equipe equipe,Long id){
        return equipeService.isValid(equipe,id);
     }


    @PostMapping("/changeV/{idEquipe}/{idDetail}")
    public void ChangeValid(@PathVariable("idEquipe") Long id,@PathVariable("idDetail") Long idDetail){

 DetailEquipe detailEquipe =detailEquipeRepository.findById(idDetail).orElseThrow();
        List<Equipe> list= (List<Equipe>) repo.findAll();
        for(Equipe equipe:list){
            if(equipe.getIdEquipe()==id && equipe.getDetailEquipe().getIdDetailEquipe()== detailEquipe.getIdDetailEquipe()) {
                detailEquipe.setDateActivation(new Date());
                if (equipe.getIsValid() == true) {



                    repo.changeValiditeEquipe(id, false);
                    System.out.println("*****************biennnnnnnnnnn**************************");
                } else {
                    repo.changeValiditeEquipe(id, true);
                    System.out.println("*****************Nooooooooooooo**************************");
                }
            }
        }
    }

    @PostMapping("/changeDelete/{idEquipe}/{idDetail}")
    public void ChangeDelete(@PathVariable("idEquipe") Long id,@PathVariable("idDetail") Long idDetail){
        DetailEquipe detailEquipe =detailEquipeRepository.findById(idDetail).orElseThrow();
        List<Equipe> list= (List<Equipe>) repo.findAll();
        for(Equipe equipe:list){
            if(equipe.getIdEquipe()==id && equipe.getDetailEquipe().getIdDetailEquipe()== detailEquipe.getIdDetailEquipe()) {
                detailEquipe.setDateSuppression(new Date());
                if (equipe.getIsDeleted() == true) {
                repo.changeDeleteEquipe(id,false);
                System.out.println("*****************biennnnnnnnnnn**************************");
            }
            else {
                    repo.changeDeleteEquipe(id, true);
                    System.out.println("*****************Nooooooooooooo**************************");
                }
            }
            }
    }
    @PostMapping("/AddEquipe")
    public Equipe AddEquipe(@RequestBody Equipe equipe){

       equipeService.saveEquipe(equipe);
       return equipe;
    }

    @PutMapping("updateEquipe/{id}")
    public Equipe updateEquipe(@RequestBody Equipe equipe, @PathVariable Long id  ){

        return equipeService.updateEquipe(equipe,id);
    }


}
