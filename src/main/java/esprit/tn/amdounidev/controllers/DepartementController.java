package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.IDepartementService;
import esprit.tn.amdounidev.entities.Departement;
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
@RequestMapping("Departement")
@Tag(name ="Departement" ,description = "Gestion de Departement ")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartementController {

    @Autowired
    IDepartementService ds;

    @Operation(summary = "Add Departement", description = "Ajouter une Departement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ajout Avec Succ",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Ajout failed",content = @Content)
    })

    @PostMapping("addDepartment")
    public Departement addDepartment(@RequestBody Departement d) {
        return ds.addDepartment(d);
    }

    @Operation(summary = "Add List Departement", description = "Ajouter une liste des Departement ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ajout Avec Succ",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Ajout failed",content = @Content)
    })

    @PostMapping("addDepartments")
    public List<Departement> addDepartment(@RequestBody List<Departement> listDepartment) {
        return ds.addDepartment(listDepartment);
    }

    @Operation(summary = "Update a Departement", description = "Modifier une Departement ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modifier Avec Succ",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Modifier failed",content = @Content)
    })

    @PutMapping("updateDepartment/{id}")
    public Departement updateDepartment(@RequestBody Departement d,@PathVariable long id) {
        return ds.updateDepartment(d,id);
    }

    @Operation(summary = "Update list Universite", description = "Modifier une liste des Universites ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modifier Avec Succ",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Modifier failed",content = @Content)
    })

    @PutMapping("updateDepartments")
    public List<Departement> updateDepartment(@RequestBody List<Departement> listDepartment) {
        return ds.updateDepartments(listDepartment);
    }

    @Operation(summary = "Delete a Departement By ID", description = "Supprimer une Departement par sont id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supp Avec Succ",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Supp failed",content = @Content)
    })

    @DeleteMapping("deleteDepartmentbyId")
    public void deleteDepartment(@RequestParam Long id) {
        ds.deleteDepartment(id);
    }

    @Operation(summary = "Delete a Departement", description = "Supprimer une Departement ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supp Avec Succ",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Supp failed",content = @Content)
    })

    @DeleteMapping("deleteDepartment")
    public void deleteDepartment(@RequestBody Departement d) {
        ds.deleteDepartment(d);
    }

    @Operation(summary = "Get AllDepartement", description = "Returns la liste des Departement ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Universite",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Universite not found",content = @Content)
    })

    @GetMapping("findAllDepartments")
    public List<Departement> findAllDepartment() {
        return ds.findAllDepartment();
    }

    @Operation(summary = "Get Departement par son id", description = "Returns Departement specifique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Universite",content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",content = @Content),
            @ApiResponse(responseCode = "404", description = "Universite not found",content = @Content)
    })

    @GetMapping("findDepartmentById")
    public Departement findDepartmentById(@RequestParam Long id) {
        return ds.findDepartmentById(id);
    }


}
