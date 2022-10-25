package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.DetailEquipeService;
import esprit.tn.amdounidev.Services.DetailEquipeServiceImpl;
import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
