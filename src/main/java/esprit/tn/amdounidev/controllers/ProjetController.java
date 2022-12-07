package esprit.tn.amdounidev.controllers;

import com.google.firebase.messaging.FirebaseMessaging;
import esprit.tn.amdounidev.Repository.ProjetRepository;
import esprit.tn.amdounidev.Services.IProjetService;
import esprit.tn.amdounidev.Services.ITacheService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import esprit.tn.amdounidev.entities.Projet;
import esprit.tn.amdounidev.entities.Tache;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("Projet")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name ="Projet" ,description = "Gestion de Projets ")
public class ProjetController {
    @Autowired
    IProjetService ps;
    ITacheService ts;
    ProjetRepository pr;

    @Operation(summary = "Add Project", description = "Ajouter un projet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed", content = @Content)
    })
    @PostMapping("addProjet")
    public Projet addProjet(@RequestBody Projet p) {
        if (p.getTaches() != null) {


            for (Tache t : p.getTaches()) {


                t.setProjet(p);
            }
            return ps.addProjet(p);
        } else
            return ps.addProjet(p);
    }


    @Operation(summary = "Add List Project", description = "Ajouter une liste des projets ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Add failed", content = @Content)
    })
    @PostMapping("addProjets")
    public List<Projet> addProjet(@RequestBody List<Projet> listProjet) {

        for (Projet p : listProjet) {

            if (p.getTaches() != null) {


                for (Tache t : p.getTaches()) {
                    t.setProjet(p);
                }

            }

        }

        return ps.addProjet(listProjet);


    }

    @Operation(summary = "Update project", description = "Modifier un projet ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "update failed", content = @Content)
    })
    @PutMapping("updateProjet/{idProjet}")
    public Projet updateProjet(@PathVariable("idProjet") Long id, @RequestBody Projet p) {


        return ps.updateProjet(p, id);
    }


    @Operation(summary = "update List Project", description = "modifier  des projets ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "update failed", content = @Content)
    })
    @PutMapping("updateProjets")
    public List<Projet> updateProjet(@RequestBody List<Projet> listProjet) {
        return ps.addProjet(listProjet);
    }

    @Operation(summary = "Delete a project  By ID", description = "Supprimer un projet  par son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "delete failed", content = @Content)
    })


    @DeleteMapping("deleteProjetbyId/{idProjet}")
    public void deleteProjet(@PathVariable("idProjet") Long id) {
        ps.deleteProjet(id);
    }


    @Operation(summary = "Delete a project  ", description = "Supprimer un projet  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "delete failed", content = @Content)
    })
    @DeleteMapping("deleteProjet")
    public void deleteProjet(@RequestBody Projet p) {
        ps.deleteProjet(p);
    }


    @Operation(summary = "Get AllProject", description = "Afficher la liste des projets ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Get not found", content = @Content)
    })
    @GetMapping("findAllProjets/{pageNo}/{pageSize}")
    public List<Projet> findAllProjet(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return ps.findAllProjet(pageNo, pageSize);

    }


    @Operation(summary = "Get project by id", description = "Afficher un projet par son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Get not found", content = @Content)
    })

    @GetMapping("findProjectById/{idProjet}")
    public Projet findProjetById(@PathVariable("idProjet") Long id) {

        return ps.findProjetById(id);
    }


    @GetMapping("findProjectByName/{nom}")
    public Projet findProjectByName(@PathVariable("nom") String nom) {
        return ps.findProjectByName(nom);
    }

    @PostMapping("affectTache/{idProjet}/{idTache}")
    public void affecterTacheToProjet(@PathVariable("idProjet") Long idProjet, @PathVariable("idTache") Long idTache) {

        ps.aassignProjetToTache(idProjet, idTache);

    }
   /* @GetMapping("findTachesByProjet/{idProjet}")
    public List<Tache> findTachesByProjet(@PathVariable("idProjet") Long idProjet) {

      return ps.getTachesByProjet(idProjet);

    }
*/

    @GetMapping("findByTypePFEProjet")
    public int findByTypePFEProjet() {
        return ps.findByTypePFEProjet();
    }

    @GetMapping("findByTypePIDEVProjet")
    public int findByTypePIDEVProjet() {
        return ps.findByTypePIDEVProjet();
    }

    @GetMapping("findByTypeJEUVIDEOProjet")
    public int findByTypeJEUVIDEOProjet() {
        return ps.findByTypeJEUVIDEOProjet();
    }


    @GetMapping("suppressionAutomatique")
    public int envoiSMS() {
        return ps.deleteAuto();
    }

}
