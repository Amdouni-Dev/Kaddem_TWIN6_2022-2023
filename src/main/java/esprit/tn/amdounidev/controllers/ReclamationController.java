package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.ReclamationService;
import esprit.tn.amdounidev.entities.Reclamation;
import esprit.tn.amdounidev.entities.Tache;
import esprit.tn.amdounidev.entities.Universite;
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
@RequestMapping("Reclamation")
@CrossOrigin(origins = "http://localhost:4200")
//@Tag --> annotation de swagger
@Tag(name = "Reclamation", description = "Gestion des reclamations")
public class ReclamationController {

    @Autowired
    ReclamationService reclamationService;


    /********************************Add Reclamation************************************/
    @Operation(summary = "Add Reclamation", description = "Ajouter une nouvelle reclamation ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("addRec")
    public Reclamation addReclamation(@RequestBody Reclamation reclamation){
        return reclamationService.saveReclamation(reclamation);
    }


    /********************************Add Reclamations************************************/
    @Operation(summary = "Add Reclamation", description = "Ajouter une liste de reclamations ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("addRec")
    public List<Reclamation> addReclamations(@RequestBody List<Reclamation> listReclamations){
        return reclamationService.saveReclamations(listReclamations);
    }


    /********************************Update Reclamation************************************/
    @Operation(summary = "Update Reclamation", description = "Mettre à jour une reclamation ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Update failed",content = @Content)
    })
    @PutMapping("updateRec")
    public Reclamation updateReclamation(@RequestBody Reclamation reclamation, @PathVariable("idRec") Long id){
        return reclamationService.updateReclamation(reclamation,id);
    }


    /********************************Delete Reclamation************************************/
    @Operation(summary = "Delete Reclamation", description = "Supprimer une reclamation ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Delete failed",content = @Content)
    })
    @DeleteMapping("deleteRec")
    public void deleteReclamation(@RequestBody Reclamation reclamation){
        reclamationService.deleteReclamation(reclamation);
    }


    /********************************Delete Reclamation By Id************************************/
    @Operation(summary = "Add Contrat", description = "Supprimer une reclamation ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Delete failed",content = @Content)
    })
    @DeleteMapping("deleteByIdRec")
    public void deleteRecById(@PathVariable("idRec") Long id){
        reclamationService.deleteReclamationById(id);
    }


    /********************************Get Reclamations************************************/
    @Operation(summary = "Get All Reclamations", description = "Retourne la liste des Reclamations ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Universite",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Empty List ",content = @Content)
    })
    @GetMapping("listeReclamations")
    public List<Reclamation> listeReclamations(){
        return reclamationService.listeReclamations();
    }
}
