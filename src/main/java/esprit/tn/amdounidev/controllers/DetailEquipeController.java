package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.DetailEquipeService;
import esprit.tn.amdounidev.Services.DetailEquipeServiceImpl;
import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/DetailEquipe")
public class DetailEquipeController {

    @Autowired
    DetailEquipeServiceImpl detailEquipeService;
    @GetMapping
    public String test(){
        return"hello";
            }
    @PostMapping ("AddDetailEquipe")
    public DetailEquipe addDetailEquipe(DetailEquipe detailEquipe){
        return detailEquipeService.addDetailEquipe(detailEquipe);
    }
    @GetMapping("/AllDetailsEquipes")
    public List<DetailEquipe> findDetailsList(){
        return (List<DetailEquipe>) detailEquipeService.findAllDetailEquipe();
    }

    @GetMapping ("Detail/{id}")
    public DetailEquipe getDetailById(@PathVariable Long id){

        return detailEquipeService.findDetailEquipeById(id);
    }


    @PostMapping ("/AddDetail")
    public DetailEquipe AddDetail(@RequestBody DetailEquipe detailEquipe){

        detailEquipeService.addDetailEquipe(detailEquipe);
        return detailEquipe;
    }

    @PutMapping("updateDetail")
    public DetailEquipe updateDetail(@RequestBody DetailEquipe detailEquipe ){

        return detailEquipeService.updateDetailEquipe(detailEquipe);
    }
}
