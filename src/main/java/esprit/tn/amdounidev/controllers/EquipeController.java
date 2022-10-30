package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Exceptions.EquipeNotFoundException;
import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Services.EquipeServiceImpl;
import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipe")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Gestion des equipes")

public class EquipeController {

    @Autowired
    EquipeServiceImpl equipeService;
    @Autowired
    EquipeRepository repo;
    @Autowired
    DetailEquipeRepository detailEquipeRepository;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien ! "),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Toutes les equipes ",description = "Afficher la liste des equipes seulement ")
    @GetMapping("/All")
    public List<Equipe> findEquipeList() {
        return (List<Equipe>) repo.findAll();
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien ! "),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Toutes les equipes + Details",description = "Afficher la liste des equipes avec la liste des details equipes ")
    @GetMapping("/equipesAndDetails")
    public List<DetailEquipe> equipeAndDetails() {
        return repo.equipesAndDetails();
    }
    // notes = "Equipe  doit s'exister")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien ! "),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide"),
            @ApiResponse(responseCode = "404", description = "!!!! equipe inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Changer la validité d\'une equipe",description = "changer la validité d'une equipe, par defaut une equipe est non validée(false)")
    @PostMapping("/ChangeValiditeEquipe/{id}")
    public Boolean ChangeIsValid(Equipe equipe, Long id) {
        return equipeService.isValid(equipe, id);
    }

    @Operation(summary = "Changer la validité d\'une equipe",description = "changer la validité d'une equipe, par defaut une equipe est non validée(false) \n En plus de changer la date d'activation d'une equipe")
    // @RouterOperation(operation = @Operation(description = "changer la validité d'une equipe, par defaut une equipe est non validée(false) \n En plus de changer la date d'activation d'une equipe"
    //       ,responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Equipe.class)))))
    //@ApiOperation(value = "Gets customer by ID",
    //   response = Equipe.class,
    // notes = "Equipe  doit s'exister")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Changé"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide"),
            @ApiResponse(responseCode = "404", description = "!!!! equipe inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @GetMapping("/changeV/{idEquipe}/{idDetail}")
    public void ChangeValid(@PathVariable("idEquipe") Long id, @PathVariable("idDetail") Long idDetail) {

        DetailEquipe detailEquipe = detailEquipeRepository.findById(idDetail).orElseThrow(() -> new EquipeNotFoundException(id));
        List<Equipe> list = (List<Equipe>) repo.findAll();
        for (Equipe equipe : list) {
            if (equipe.getIdEquipe() == id && equipe.getDetailEquipe().getIdDetailEquipe() == detailEquipe.getIdDetailEquipe()) {
                detailEquipe.setDateActivation(new Date());
                if (equipe.getIsValid() == true) {
                    repo.changeValiditeEquipe(id, false);
                    System.out.println("*****************biennnnnnnnnnn**************************");
                } else {
                    repo.changeValiditeEquipe(id, true);
                    System.out.println("*****************Nooooooooooooo**************************");
                }
            }
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Supprimé"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide(detail ou equipe)"),
            @ApiResponse(responseCode = "404", description = "!!!! equipe et/ou detail equipe  inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Changer l\'etat de suppression d\'une equipe",description = "changer l'etat de suppression d'une equipe +changement de la date de suppression d'une equipe")
    @PostMapping("/changeDelete/{idEquipe}/{idDetail}")
    public void ChangeDelete(@PathVariable("idEquipe") Long id, @PathVariable("idDetail") Long idDetail) {
        DetailEquipe detailEquipe = detailEquipeRepository.findById(idDetail).orElseThrow();
        List<Equipe> list = (List<Equipe>) repo.findAll();
        for (Equipe equipe : list) {
            if (equipe.getIdEquipe() == id && equipe.getDetailEquipe().getIdDetailEquipe() == detailEquipe.getIdDetailEquipe()) {
                detailEquipe.setDateSuppression(new Date());
                if (equipe.getIsDeleted() == true) {
                    repo.changeDeleteEquipe(id, false);
                    System.out.println("*****************biennnnnnnnnnn**************************");
                } else {
                    repo.changeDeleteEquipe(id, true);
                    System.out.println("*****************Nooooooooooooo**************************");
                }
            }
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(summary = "Ajouter une equipe",description = "ajouter une equipe")
    @PostMapping("/AddEquipe")
    public void AddEquipe(@RequestBody Equipe equipe) {

        equipeService.saveEquipe(equipe);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Ajouté"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper des champs valides"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})
    @Operation(description = "Ajouter une equipe ainsi que ses details")
    @PostMapping("/AddEquipeAndDetail")
    public void AddEquipeAndDetail(@RequestBody Equipe equipe, @RequestBody DetailEquipe detailEquipe) {

        equipeService.saveEquipeAndDetail(equipe, detailEquipe);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien mis a jour"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide"),
            @ApiResponse(responseCode = "404", description = "!!!!  equipe  inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})

    @Operation(summary = "Mettre a jour une equipe",description = "Mettre a jour une equipe via son id")
    @PutMapping("updateEquipe/{id}")
    public void updateEquipe(@RequestBody Equipe equipe, @PathVariable Long id) {

        equipeService.updateEquipe(equipe, id);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien !!"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide(detail ou equipe)"),
            @ApiResponse(responseCode = "404", description = "!!!! equipe  inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})

    @Operation(summary = "Afficher une  equipe",description = "Retourner une equipe via son id")
    @GetMapping("equipe/{id}")
    public Equipe getEquipeById(@PathVariable Long id) {

        return repo.findById(id).orElseThrow(() -> new EquipeNotFoundException(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bien Supprimé"),
            @ApiResponse(responseCode = "400", description = "Essayer de taper un identifiant valide"),
            @ApiResponse(responseCode = "404", description = "!!!!  equipe  inexistante"),
            @ApiResponse(responseCode = "500", description = "erreur serveur")})

    @Operation(summary = "Supprimer une equipe",description = "Supprimer definitivement une equipe")
    @DeleteMapping("deleteEquipe/{id}")
    public void deleteEquipeById(@PathVariable Long id) {
        equipeService.deleteEquipeById(id);
    }

}
