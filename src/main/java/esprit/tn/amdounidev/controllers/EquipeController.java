package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Services.EquipeServiceImpl;
import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipe")
@CrossOrigin(origins = "http://localhost:4200")
public class EquipeController {

    @Autowired
    EquipeServiceImpl equipeService;
    @Autowired
    EquipeRepository repo;
    @Autowired
    DetailEquipeRepository detailEquipeRepository;

    @GetMapping("/All")
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


    @GetMapping("/changeV/{idEquipe}/{idDetail}")
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
    public void AddEquipe(@RequestBody Equipe equipe){

       equipeService.saveEquipe(equipe);

    }
    @PostMapping("/AddEquipeAndDetail")
    public void AddEquipeAndDetail(@RequestBody Equipe equipe,@RequestBody DetailEquipe detailEquipe){

        equipeService.saveEquipeAndDetail(equipe,detailEquipe);

    }

   // @PutMapping("updateEquipe/{id}")
    @PutMapping("updateEquipe/{id}")
    public void updateEquipe(@RequestBody Equipe equipe, @PathVariable Long id  ){

          equipeService.updateEquipe(equipe,id);

    }

    @GetMapping("equipe/{id}")
    public Optional<Equipe> getEquipeById(@PathVariable Long id){

        return repo.findById(id);
    }
@DeleteMapping("deleteEquipe/{id}")
public void deleteEquipeById(@PathVariable Long id){
        equipeService.deleteEquipeById(id);
}

}
