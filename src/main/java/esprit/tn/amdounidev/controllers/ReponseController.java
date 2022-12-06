package esprit.tn.amdounidev.controllers;


import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Repository.ReponseRepository;
import esprit.tn.amdounidev.Services.EquipeServiceImpl;
import esprit.tn.amdounidev.Services.ReponseServiceImpl;
import esprit.tn.amdounidev.entities.Departement;
import esprit.tn.amdounidev.entities.Reponse;
import esprit.tn.amdounidev.entities.Thread;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Reponse")
public class ReponseController {
    @Autowired
    ReponseRepository rr;
    @Autowired
    ReponseServiceImpl ReponseService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Thread list "),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Threads ",description = "Thread list")
    @GetMapping("/")
    public ResponseEntity<List<Reponse>> findReponseList(){
        return new ResponseEntity <List<Reponse>>(rr.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Reponse findReponseById( @PathVariable Long id) {
        return ReponseService.findReponseById(id);
    }


    @PostMapping("/AddReponse")
    public Reponse  AddReponse(@RequestBody Reponse Reponse){

        return ReponseService.addReponse(Reponse);

    }

    @PostMapping("/updateReponse")
    public Reponse updateReponse(@RequestBody Reponse Reponse){
        return ReponseService.updateReponse(Reponse);
    }
    @GetMapping("/deleteReponse/{id}")
    public ResponseEntity<?> deleteDepartment( @PathVariable Long id) {
        ReponseService.deleteReponse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
