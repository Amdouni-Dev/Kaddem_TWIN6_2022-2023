package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.ITacheService;
import esprit.tn.amdounidev.entities.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("Tache")
public class TacheController {
    @Autowired
    ITacheService TacheS;
    @PostMapping("addTache")
    public Tache addTache(@RequestBody Tache t) {
        return  TacheS.addTache(t);
    }

    @PostMapping("addTaches")
    public List<Tache> addTache(@RequestBody List<Tache> listTache) {
        return TacheS.addTache(listTache);
    }

    @PutMapping("updateTache")
    public Tache updateTache(@RequestBody Tache t) {
        return  TacheS.addTache(t);
    }

    @PutMapping("updateTaches")
    public List<Tache> updateTache(@RequestBody List<Tache> listTache) {
        return TacheS.addTache(listTache);
    }

    @DeleteMapping("deleteTachebyId")
    public void deleteTache( @RequestParam Long id) {
        TacheS.deleteTache(id);
    }

    @DeleteMapping("deleteTache")
    public void deleteTache( @RequestBody Tache t) {
        TacheS.deleteTache(t);
    }

    @GetMapping("findAllTaches")
    public List<Tache> findAllTache() {
        return TacheS.findAllTache();
    }

    @GetMapping("findTacheById")
    public Tache findTacheById( @RequestParam Long id) {
        return TacheS.findTacheById(id);
    }
}
