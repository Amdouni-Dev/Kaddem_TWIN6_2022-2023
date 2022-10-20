package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Services.EquipeService;
import esprit.tn.amdounidev.Services.EquipeServiceImpl;
import esprit.tn.amdounidev.entities.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@RestController
public class EquipeController {
    @Autowired
    EquipeServiceImpl equipeService;
    @Autowired
    EquipeRepository repo;

    @GetMapping("/equipes")
    public List<Equipe> findEquipeList(){
         return equipeService.findEquipes();
     }



     @PostMapping("/ChangeValiditeEquipe/{id}")
     public Boolean ChangeIsValid(Equipe equipe,Long id){
        return equipeService.isValid(equipe,id);
     }


    @PostMapping("/changeV/{idEquipe}")
    public void ChangeValid(@PathVariable("idEquipe") long id){
        List<Equipe> list=findEquipeList();
        for(Equipe equipe:list){
           if(equipe.getIdEquipe()==id && equipe.getIsValid()== true){
               repo.changeValiditeEquipe(id,false);
               System.out.println("*****************biennnnnnnnnnn**************************");
           }
           else
               repo.changeValiditeEquipe(id,true);
               System.out.println("*****************Nooooooooooooo**************************");
        }
    }

    @PostMapping("/changeDelete/{idEquipe}")
    public void ChangeDelete(@PathVariable("idEquipe") long id){
        List<Equipe> list=findEquipeList();
        for(Equipe equipe:list){
            if(equipe.getIdEquipe()==id && equipe.getIsDeleted()== true){
                repo.changeDeleteEquipe(id,false);
                System.out.println("*****************biennnnnnnnnnn**************************");
            }
            else
                repo.changeDeleteEquipe(id,true);
            System.out.println("*****************Nooooooooooooo**************************");
        }
    }
    @PostMapping("/AddEquipe")
    public void AddEquipe(Equipe equipe){
        equipeService.saveEquipe(equipe);
    }


}
