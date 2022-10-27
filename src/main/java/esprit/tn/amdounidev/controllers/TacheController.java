package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.ITacheService;
import esprit.tn.amdounidev.entities.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("Tache")
@CrossOrigin(origins = "http://localhost:4200")
public class TacheController {
    @Autowired
    ITacheService ts;
    @PostMapping("addTache")
    public Tache addTache(@RequestBody Tache t) {
        return  ts.addTache(t);
    }

    @PostMapping("addTaches")
    public List<Tache> addTache(@RequestBody List<Tache> listTache) {
        return ts.addTache(listTache);
    }

    @PutMapping("updateTache")
    public Tache updateTache(@RequestBody Tache t) {
        return  ts.addTache(t);
    }

    @PutMapping("updateTaches")
    public List<Tache> updateTache(@RequestBody List<Tache> listTache) {
        return ts.addTache(listTache);
    }

    @DeleteMapping("deleteTachebyId/{idTache}")
    public void deleteTache( @PathVariable("idTache") Long id) {
        ts.deleteTache(id);
    }

    @DeleteMapping("deleteTache")
    public void deleteTache( @RequestBody Tache t) {
        ts.deleteTache(t);
    }

    @GetMapping("findAllTaches")
    public List<Tache> findAllTache() {
        return ts.findAllTache();
    }

    @GetMapping("findTacheById/{idTache}")
    public Tache findTacheById( @RequestParam("idTache") Long id) {
        return ts.findTacheById(id);
    }
}
