package esprit.tn.amdounidev.controllers;


import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Repository.ReponseRepository;
import esprit.tn.amdounidev.Services.EquipeServiceImpl;
import esprit.tn.amdounidev.Services.ReponseServiceImpl;
import esprit.tn.amdounidev.entities.Departement;
import esprit.tn.amdounidev.entities.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Reponse")
public class ReponseController {
    @Autowired
    ReponseRepository rr;
    @Autowired
    ReponseServiceImpl ReponseService;

    @GetMapping("/")
    public List<Reponse> findReponseList(){
        return (List<Reponse>) rr.findAll();
    }

    @GetMapping("/{id}")
    public Reponse findReponseById( @PathVariable Long id) {
        return ReponseService.findReponseById(id);
    }

    @PostMapping("/AddReponse")
    public Reponse AddReponse(@RequestBody Reponse Reponse){

        ReponseService.addReponse(Reponse);
        return Reponse;
    }

    @PostMapping("/updateReponse")
    public Reponse updateReponse(@RequestBody Reponse Reponse){
        return ReponseService.updateReponse(Reponse);
    }
    @GetMapping("/deleteReponse/{id}")
    public void deleteDepartment( @PathVariable Long id) {
        ReponseService.deleteReponse(id);
    }
}
