package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.IUniversiteService;
import esprit.tn.amdounidev.entities.Universite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        @GetMapping("findAllUniversite")
    public List<Universite> findAllUniversite() {
        return us.findAllUniversite();
    }

    @GetMapping("findUniversiteById")
    public Universite findUniversiteById( @RequestParam Long id) {
        return us.findUniversiteById(id);
    }

}
