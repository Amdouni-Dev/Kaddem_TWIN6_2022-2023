package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.IUniversiteService;
import esprit.tn.amdounidev.entities.Universite;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

@RestController
//@Component ou @ResponseBody
//@Controller ou @ResponseBody
@RequestMapping("Universite")
public class UniversiteController {

    @Autowired
    IUniversiteService us;

    @PostMapping("addUniversite")
    public Universite addUniversite(@RequestBody Universite d) {
        return  us.addUniversite(d);
    }

    @PostMapping("addUniversites")
    public List<Universite> addUniversite(@RequestBody List<Universite> listDepartment) {
        return us.addUniversite(listDepartment);
    }

    @PutMapping("updateUniversite")
    public Universite updateUniversite(@RequestBody Universite d) {
        return  us.updateUniversite(d);
    }

    @PutMapping("updateUniversites")
    public List<Universite> updateUniversite(@RequestBody List<Universite> listDepartment) {
        return us.updateUniversite(listDepartment);
    }

    @DeleteMapping("deleteUniversite")
    public void deleteUniversite( @RequestParam Long id) {
        us.deleteUniversite(id);
    }

    @DeleteMapping("deleteUniversites")
    public void deleteUniversite( @RequestBody Universite d) {
        us.deleteUniversite(d);
    }

    @Operation(summary = "Get a AllUniversite ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found All Universite",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "404", description = "Universites not found",
                    content = @Content) })

    @GetMapping("findAllUniversite")
    public List<Universite> findAllUniversite() {
        return us.findAllUniversite();
    }

    @Operation(summary = "Get a Universite by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Universite",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Universite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Universite not found",
                    content = @Content) })

    @GetMapping("findUniversiteById")
    public Universite findUniversiteById(@Parameter(description = "id of book to be searched")  @RequestParam Long id ) {
        return us.findUniversiteById(id);
    }

    @GetMapping("findUniversiteByNom")
    public Universite findUniversiteById( @RequestParam String Nom) {
        return us.findBynomUniversite(Nom);
    }
}
