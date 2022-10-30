package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.IProjetService;
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
@RequestMapping("Projet")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name ="Projet" ,description = "Gestion de Projets ")
public class ProjetController {
    @Autowired
    IProjetService ps;

    @Operation(summary = "Add Project", description = "Ajouter un projet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("addProjet")
    public Projet addProjet(@RequestBody Projet p) {
        return  ps.addProjet(p);
    }


    @Operation(summary = "Add List Project", description = "Ajouter une liste des projets ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("addProjets")
    public List<Projet> addProjet(@RequestBody List<Projet> listProjet) {
        return ps.addProjet(listProjet);
    }

    @Operation(summary = "Update project", description = "Modifier un projet ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "update failed",content = @Content)
    })
    @PutMapping("updateProjet")
    public Projet updateProjet(@RequestBody Projet p) {
        return  ps.addProjet(p);
    }


    @Operation(summary = "update List Project", description = "modifier  des projets ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "update failed",content = @Content)
    })
    @PutMapping("updateProjets")
    public List<Projet> updateProjet(@RequestBody List<Projet> listProjet) {
        return ps.addProjet(listProjet);
    }

    @Operation(summary = "Delete a project  By ID", description = "Supprimer un projet  par son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "delete failed",content = @Content)
    })


    @DeleteMapping("deleteProjetbyId/{idProjet}")
    public void deleteProjet( @PathVariable("idProjet") Long id) {
        ps.deleteProjet(id);
    }



    @Operation(summary = "Delete a project  ", description = "Supprimer un projet  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "delete failed",content = @Content)
    })
    @DeleteMapping("deleteProjet")
    public void deleteProjet( @RequestBody Projet p) {
        ps.deleteProjet(p);
    }


    @Operation(summary = "Get AllProject", description = "Afficher la liste des projets ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Get not found",content = @Content)
    })
    @GetMapping("findAllProjets")
    public List<Projet> findAllProjet() {
        return ps.findAllProjet();
    }


    @Operation(summary = "Get project by id", description = "Afficher un projet par son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get successfully",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Get not found",content = @Content)
    })

    @GetMapping("findProjetById/{idProjet}")
    public Projet findProjetById( @RequestParam("idProjet") Long id) {
        return ps.findProjetById(id);
    }
}
