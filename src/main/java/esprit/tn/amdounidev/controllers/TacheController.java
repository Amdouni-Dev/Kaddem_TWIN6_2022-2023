package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Repository.ProjetRepository;
import esprit.tn.amdounidev.Services.ITacheService;
import esprit.tn.amdounidev.entities.Departement;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("Tache")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name ="Tache" ,description = "Gestion de Taches ")
public class TacheController {
    @Autowired
    ITacheService ts;
    @Autowired //ou @Inject
    ProjetRepository pr;


    @Operation(summary = "Add Task", description = "Ajouter une tache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })


    @PostMapping("addTache")
    public Tache addTache(@RequestBody Tache t) {
        return  ts.addTache(t);
    }

    @Operation(summary = "Add List Tasks", description = "Ajouter une liste des taches ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("addTaches")
    public List<Tache> addTache(@RequestBody List<Tache> listTache) {
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


    @DeleteMapping("deleteTachebyId/{idTache}")
    public void deleteTache( @PathVariable("idTache") Long id) {
        ts.deleteTache(id);
    }


    @Operation(summary = "Delete a task  ", description = "Supprimer une tache  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Projet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "delete failed",content = @Content)
    })
    @DeleteMapping("deleteTache")
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
    public Tache findTacheById( @RequestParam("idTache") Long id) {
        return ts.findTacheById(id);
    }


    @PostMapping("affectProjet/{idProjet}/{idTache}")
    public void affecterProjectToTache(@PathVariable("idProjet") Long idProjet,@PathVariable("idTache") Long idTache) {

        ts.aassignProjetToTache(idProjet,idTache);

    }







    @GetMapping("findProjet/{idTache}")
    public Projet findProjet(@PathVariable("idTache") Long idTache) {

        return  ts.getProjetByTache(idTache);

    }
}
