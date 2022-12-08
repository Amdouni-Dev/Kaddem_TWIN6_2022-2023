package esprit.tn.amdounidev.controllers;

import com.google.firebase.messaging.FirebaseMessagingException;
import esprit.tn.amdounidev.Repository.EtudiantRepository;
import esprit.tn.amdounidev.Repository.ProjetRepository;
import esprit.tn.amdounidev.Repository.TacheRepository;
import esprit.tn.amdounidev.Services.FirebaseService;
import esprit.tn.amdounidev.Services.ITacheService;
import esprit.tn.amdounidev.entities.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("Tache")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name ="Tache" ,description = "Gestion de Taches ")
public class TacheController {
    @Autowired
    ITacheService ts;
    @Autowired
    FirebaseService fs;
    @Autowired //ou @Inject
    ProjetRepository pr;
@Autowired
    TacheRepository tr;

    @Autowired
    EtudiantRepository er;

    @Operation(summary = "Add Task", description = "Ajouter une tache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })


    @PostMapping("addTache/{idProjet}/{idEtudiant}")
    public Tache addTache(@RequestBody Tache t ,@PathVariable("idProjet") Long idProjet, @PathVariable("idEtudiant") Long idEtudiant) {
        Projet p =pr.findByIdProjet(idProjet);
        Etudiant e=er.findByIdEtudiant(idEtudiant);
        t.setProjet(p);
        t.setEtudiant(e);
        return  ts.addTache(t);
    }

    @Operation(summary = "Add List Tasks", description = "Ajouter une liste des taches ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("addTaches/{idProjet}/{idEtudiant}")
    public List<Tache> addTache(@RequestBody List<Tache> listTache , @PathVariable("idProjet") Long idProjet, @PathVariable("idEtudiant") Long idEtudiant) {

        Projet p =pr.findByIdProjet(idProjet);
        Etudiant e=er.findByIdEtudiant(idEtudiant);
        for(Tache t :listTache ){
            t.setProjet(p);
            t.setEtudiant(e);
        }
        return ts.addTache(listTache);
    }

    @Operation(summary = "Update task", description = "Modifier une tache ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "update failed",content = @Content)
    })

    @PutMapping("updateTache")
    public Tache updateTache(@RequestBody Tache t) {

        t.setProjet(t.getProjet());
        return  ts.addTache(t);
    }


    @Operation(summary = "update List tasks", description = "modifier  des taches ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "update failed",content = @Content)
    })
    @PutMapping("updateTaches")
    public List<Tache> updateTache(@RequestBody List<Tache> listTache) {


        return ts.addTache(listTache);
    }


    @Operation(summary = "Delete a task  By ID", description = "Supprimer une tache  par son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "delete failed",content = @Content)
    })





    @DeleteMapping("deleteTacheById/{idTache}")
    public void deleteTache(@PathVariable("idTache") Long id) {
       ts.deleteTache(id);
    }


    @Operation(summary = "Delete a task  ", description = "Supprimer une tache  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "delete failed",content = @Content)
    })
    @PostMapping("deleteTache")
    public void deleteTache( @RequestBody Tache t) {
        ts.deleteTache(t);
    }

    @Operation(summary = "Get All Tasks", description = "Afficher la liste des taches ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Get not found",content = @Content)
    })
    @GetMapping("findAllTaches")
    public List<Tache> findAllTache() {
        return ts.findAllTache();
    }


    @Operation(summary = "Get task by id", description = "Afficher une tache  par son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Get not found",content = @Content)
    })


    @GetMapping("findTacheById/{idTache}")
    public Tache findTacheById( @PathVariable("idTache") Long id) {
        return ts.findTacheById(id);
    }

    @GetMapping("findTacheByNom/{nom}")
    public Tache findTacheByNom( @PathVariable("nom") String nom) {
        return tr.findByNomTache(nom);
    }


    @PostMapping("affectProjet/{idProjet}/{idTache}")
    public void affecterProjectToTache(@PathVariable("idProjet") Long idProjet,@PathVariable("idTache") Long idTache) {

        ts.aassignProjetToTache(idProjet,idTache);

    }
    @GetMapping("findTachesByProjet/{idProjet}")
    public List<Tache> findTachesByProjet(@PathVariable("idProjet") Long idProjet) {

        return ts.getTachesByProjet(idProjet);

    }


    @GetMapping("findTachesByNameProjet/{nom}")
    public List<Tache> findTachesByNameProjet(@PathVariable("nom") String nom) {

        return tr.findByTachesByNameProjet(nom);

    }




    @GetMapping("findEtudiantByTache/{idTache}")
    public Etudiant findEtudiantByTache(@PathVariable("idTache") Long idTache) {

        return  ts.getEtudiantByTache(idTache);

    }




    @GetMapping("findProjet/{idTache}")
    public Projet findProjet(@PathVariable("idTache") Long idTache) {

        return  ts.getProjetByTache(idTache);

    }

  /*  @PostMapping("sendNotif")
    public String sendNotification(@RequestBody Notee note
                                  ) throws FirebaseMessagingException {
        return fs.sendNotification(note);
    }

*/


}
