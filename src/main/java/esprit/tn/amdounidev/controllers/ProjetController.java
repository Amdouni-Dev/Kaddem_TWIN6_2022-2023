package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.IProjetService;
import esprit.tn.amdounidev.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("Projet")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjetController {
    @Autowired
    IProjetService ps;
    @PostMapping("addProjet")
    public Projet addProjet(@RequestBody Projet p) {
        return  ps.addProjet(p);
    }

    @PostMapping("addProjets")
    public List<Projet> addProjet(@RequestBody List<Projet> listProjet) {
        return ps.addProjet(listProjet);
    }

    @PutMapping("updateProjet")
    public Projet updateProjet(@RequestBody Projet p) {
        return  ps.addProjet(p);
    }

    @PutMapping("updateProjets")
    public List<Projet> updateProjet(@RequestBody List<Projet> listProjet) {
        return ps.addProjet(listProjet);
    }

    @DeleteMapping("deleteProjetbyId/{idProjet}")
    public void deleteProjet( @PathVariable("idProjet") Long id) {
        ps.deleteProjet(id);
    }

    @DeleteMapping("deleteProjet")
    public void deleteProjet( @RequestBody Projet p) {
        ps.deleteProjet(p);
    }

    @GetMapping("findAllProjets")
    public List<Projet> findAllProjet() {
        return ps.findAllProjet();
    }

    @GetMapping("findProjetById/{idProjet}")
    public Projet findProjetById( @RequestParam("idProjet") Long id) {
        return ps.findProjetById(id);
    }
}
