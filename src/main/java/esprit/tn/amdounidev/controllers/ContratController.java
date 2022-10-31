package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.ContratService;
import esprit.tn.amdounidev.entities.Contrat;
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
@RequestMapping("/Contrat")
@Tag(name = "Contrat", description = "Gestion des contrats")
@CrossOrigin(origins = "http://localhost:4200")
public class ContratController {

    @Autowired
    ContratService contratService;


    /********************************Add Contrat************************************/
    @Operation(summary = "Add Contrat", description = "Ajouter un nouveau contrat ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("addContrat")
    public  Contrat addContrat(@RequestBody Contrat contrat){
        return contratService.saveContart(contrat);
    }


    /********************************Update Contrat************************************/
    @Operation(summary = "Update Contrat", description = "Mettre à jour un contrat ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Update failed",content = @Content)
    })
    @PutMapping("updateContrat")
    public  Contrat updateContrat(@RequestBody Contrat contrat, @PathVariable("idCtrt") Long id){
        return contratService.updateContart(contrat,id);
    }


    /********************************Delete Contrat************************************/
    @Operation(summary = "Delete Contrat", description = "Supprimer un contrat ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Delete failed",content = @Content)
    })
    @DeleteMapping("deleteContrat")
    public void deleteContrat(@RequestBody Contrat contrat){
        contratService.deleteContrat(contrat);
    }


    /********************************Delete Contrat By Id************************************/
    @Operation(summary = "Delete Contrat", description = "Supprimer un contrat ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Tache.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Delete failed",content = @Content)
    })
    @DeleteMapping("deleteById")
    public void deleteContratById(@PathVariable("idCtrt") Long id){
        contratService.deleteContratById(id);
    }


    /********************************Get Contrats************************************/
    @Operation(summary = "Get All Contrats", description = "Retourne la liste des Contrats ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Universite",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Empty List    ",content = @Content)
    })
    @GetMapping("listeContrats")
    public List<Contrat> listeContrats(){
        return contratService.listeContrats();
    }


    /********************************Get Contrat************************************/
    @Operation(summary = "Get Contrat", description = "Retourne un Contrat par son id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Universite",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Contrat not found",content = @Content)
    })
    @GetMapping("contartById")
    public void findContratById (@PathVariable("idCtrt") Long id){
        contratService.findContratById(id);
    }

}
