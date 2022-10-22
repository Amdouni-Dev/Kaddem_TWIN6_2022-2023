package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.IProjetService;
import esprit.tn.amdounidev.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("Projet")
public class ProjetController {
    @Autowired
    IProjetService projetS;
    @PostMapping("addProjet")
    public Projet addProjet(@RequestBody Projet p) {
        return  projetS.addProjet(p);
    }

    @PostMapping("addProjets")
    public List<Projet> addProjet(@RequestBody List<Projet> listProjet) {
        return projetS.addProjet(listProjet);
    }

    @PutMapping("updateProjet")
    public Projet updateProjet(@RequestBody Projet p) {
        return  projetS.addProjet(p);
    }

    @PutMapping("updateProjets")
    public List<Projet> updateProjet(@RequestBody List<Projet> listProjet) {
        return projetS.addProjet(listProjet);
    }

    @DeleteMapping("deleteProjetbyId")
    public void deleteProjet( @RequestParam Long id) {
        projetS.deleteProjet(id);
    }

    @DeleteMapping("deleteProjet")
    public void deleteProjet( @RequestBody Projet p) {
        projetS.deleteProjet(p);
    }

    @GetMapping("findAllProjets")
    public List<Projet> findAllProjet() {
        return projetS.findAllProjet();
    }

    @GetMapping("findProjetById")
    public Projet findProjetById( @RequestParam Long id) {
        return projetS.findProjetById(id);
    }
}
