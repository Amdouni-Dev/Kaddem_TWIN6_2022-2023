package esprit.tn.amdounidev.controllers;

import com.lowagie.text.DocumentException;
import esprit.tn.amdounidev.Services.ContratPdfGenerator;
import esprit.tn.amdounidev.Services.ContratService;
import esprit.tn.amdounidev.entities.Contrat;
import esprit.tn.amdounidev.entities.Etudiant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
            @ApiResponse(responseCode = "200", description = "Added successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("addContrat")
    public  Contrat addContrat(@RequestBody Contrat contrat){
        return contratService.addContart(contrat);
    }


    /********************************Update Contrat************************************/
    @Operation(summary = "Update Contrat", description = "Mettre à jour un contrat ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Update failed",content = @Content)
    })
    @PutMapping("updateContrat/{idCtrt}")
    public  Contrat updateContrat(@PathVariable("idCtrt") Long id,@RequestBody Contrat contrat){
        return contratService.updateContart(id,contrat);
    }


    /********************************Delete Contrat************************************/
    @Operation(summary = "Delete Contrat", description = "Supprimer un contrat ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Delete failed",content = @Content)
    })
    @DeleteMapping("deleteContrat")
    public void deleteContrat(@RequestBody Contrat contrat){
        contratService.removeContrat(contrat);
    }


    /********************************Delete Contrat By Id************************************/
    @Operation(summary = "Delete Contrat", description = "Supprimer un contrat ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Delete failed",content = @Content)
    })
    @DeleteMapping("deleteById/{idCtrt}")
    public void deleteContratById(@PathVariable("idCtrt") Long id){
        contratService.removeContratById(id);
    }


    /********************************Get Contrats************************************/
    @Operation(summary = "Get All Contrats", description = "Retourne la liste des Contrats ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Contrat",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Empty List !",content = @Content)
    })
    @GetMapping("listeContrats")
    public Page<Contrat> listeContrats(@RequestParam(name = "page", defaultValue = "0")int page,
                                        @RequestParam(name="size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Contrat> pageResult = contratService.retrieveAllContrats(pageRequest);
        return pageResult;

    }

    /********************************Get Contrats Non Archives************************************/
    @Operation(summary = "Get All Contrats Non Archives", description = "Retourne la liste des Contrats Non Archives")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Contrat",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Empty List !",content = @Content)
    })
    @GetMapping("listeContratsNonArchives")
    public List<Contrat> listeContratsNonArchives() {
        List<Contrat> liste = contratService.contratsNonArchives();
        return liste;

    }

    /********************************Get Contrats Non Archives************************************/
    @Operation(summary = "Get All Contrats Archives", description = "Retourne la liste des Contrats Archives")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Contrat",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Empty List !",content = @Content)
    })
    @GetMapping("listeContratsArchives")
    public List<Contrat> listeContratsArchives() {
        List<Contrat> liste = contratService.contratsArchives();
        return liste;

    }

    /********************************Get Contrat************************************/
    @Operation(summary = "Get Contrat", description = "Retourne un Contrat par son id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Contrat",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Contrat not found",content = @Content)
    })
    @GetMapping("contartById")
    public void findContratById (@PathVariable("idCtrt") Long id){
        contratService.retrieveContrat(id);
    }


    /********************************Get Contrats Valides************************************/
    @Operation(summary = "Get Contrats Valides", description = "Retourne liste des contrats valides ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Contrats",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Contrats not found",content = @Content)
    })
    @GetMapping("valid/{dateDebutC}/{dateFinC}/{idContrat}")
    public void contratsValides(@RequestParam("dateDebutC") Date dateDebutC,
                               @RequestParam("dateFinC") Date dateFinC){
        contratService.nbContratsValides(dateDebutC,dateFinC);
    }


    /***********************Add And Assign Etudiant To Equipe And Contrat************************/
    @Operation(summary = "Get Contrats Valides", description = "Retourne liste des contrats valides ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("AssignEEqC")
    public void addAndAssignEtudiantToEquipeAndContract(@RequestBody Etudiant etudiant, @PathVariable("idCtrt") Long idC,
                                                        @PathVariable("idEquipe") Long idE) {
        contratService.addAndAssignEtudiantToEquipeAndContract(etudiant,idC,idE);
    }

    /*******************************Affect Contrat To Etudiant***********************************/
    @Operation(summary = "Affect Contrat To Etudiant", description = "Affecter un contrat a un etudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added successfully",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Contrat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed",content = @Content)
    })
    @PostMapping("AffectCToEtud/{nom}/{prenom}")
    public void affectContratToEtudiant(@RequestBody Contrat contrat, @RequestParam("nom") String nom,
                                                        @PathVariable("prenom") String prenom) {
        contratService.affectContratToEtudiant(contrat,nom,prenom);
    }

    /*******************************Affect Contrat To Etudiant***********************************/


    /*******************************Contrat PDF***********************************/
    @GetMapping("/exportPDFC")

    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");

        String currentDateTime = dateFormat.format(new Date());

        String headerkey = "Content-Disposition";

        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";

        response.setHeader(headerkey, headervalue);

        List <Contrat> listofC = contratService.listeContrats();

        ContratPdfGenerator generator = new ContratPdfGenerator();

        generator.generate(listofC, response);

    }

}

