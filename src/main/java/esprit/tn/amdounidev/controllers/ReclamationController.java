package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.ReclamationService;
import esprit.tn.amdounidev.entities.Contrat;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @PostMapping("addListRec")
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
    @PutMapping("updateRec/{id}")
    public Reclamation updateReclamation(@RequestBody Reclamation reclamation, @PathVariable("id") Long id){
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
    @Operation(summary = "Delete Reclamation", description = "Supprimer une reclamation ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Delete failed",content = @Content)
    })
    @DeleteMapping("deleteByIdRec/{id}")
    public void deleteRecById(@PathVariable("id") Long id){
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
    public Page<Reclamation> listeReclamations(@RequestParam(name = "page", defaultValue = "0")int page,
                                               @RequestParam(name="size", defaultValue = "5") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Reclamation> pageResult =reclamationService.listeReclamations(pageRequest);
        return pageResult;
    }
    /********************************Get Reclamations Non Traitees************************************/
    @Operation(summary = "Get All Reclamations Non Traitees", description = "Retourne la liste des Reclamations Non Traitees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Reclamation",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Empty List ",content = @Content)
    })
    @GetMapping("listeReclamationsNonTraitees")
    public List<Reclamation> listeReclamationsNonTraitees(){
        List<Reclamation> liste =reclamationService.listeReclamationsNonTratitees();
        return liste;
    }
    /********************************Get Reclamations Traitees************************************/
    @Operation(summary = "Get All Reclamations Traitees", description = "Retourne la liste des Reclamations Traitees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Reclamation",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Empty List ",content = @Content)
    })
    @GetMapping("listeReclamationsTraitees")
    public List<Reclamation> listeReclamationsTraitees(){
        List<Reclamation> liste =reclamationService.listeReclamationsTratitees();
        return liste;
    }

    /********************************Traiter Reclamation************************************/
    @Operation(summary = "Set Etat True", description = "Traite une reclamation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Reclamation",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reclamation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Empty List ",content = @Content)
    })
    @PutMapping("RecTrue/{id}")
    public  void TraiterRec(@PathVariable("id") Long id,@RequestBody Reclamation  reclamation){
       reclamationService.traiterRec(id, reclamation);
    }

}
