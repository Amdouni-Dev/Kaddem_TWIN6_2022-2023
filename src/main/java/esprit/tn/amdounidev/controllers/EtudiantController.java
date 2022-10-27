package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Repository.EtudiantRepository;
import esprit.tn.amdounidev.Services.EtudiantService;
import esprit.tn.amdounidev.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Etudiant")
public class EtudiantController {
    @Autowired
    esprit.tn.amdounidev.Services.EtudiantService EtudiantService;
    @Autowired
    esprit.tn.amdounidev.Repository.EtudiantRepository repo;
    @Autowired
    EtudiantRepository EtudiantRepository;

    @GetMapping("/")
    public List<Etudiant> findEtudiantList(){
        return (List<Etudiant>) repo.findAll();
    }

    @GetMapping ("Etudiant/{id}")
    public Optional<Etudiant> getEtudiantById(@PathVariable Long id){

        return repo.findById(id);
    }


    @PostMapping ("/AddEtudiant")
    public Etudiant AddEtudiant(@RequestBody Etudiant Etudiant){

        EtudiantService.addEtudiant(Etudiant);
        return Etudiant;
    }

    @PutMapping("updateEtudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant Etudiant ){

        return EtudiantService.updateEtudiant(Etudiant);
    }
}
