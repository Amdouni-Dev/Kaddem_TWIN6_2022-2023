package esprit.tn.amdounidev.controllers;

import esprit.tn.amdounidev.Services.IDepartementService;
import esprit.tn.amdounidev.entities.Departement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Departement")
@Tag(name ="Departement" ,description = "Gestion de Departement ")
public class DepartementController {

    @Autowired
    IDepartementService ds;

    @PostMapping("addDepartment")
    public Departement addDepartment(@RequestBody Departement d) {
        return ds.addDepartment(d);
    }

    @PostMapping("addDepartments")
    public List<Departement> addDepartment(@RequestBody List<Departement> listDepartment) {
        return ds.addDepartment(listDepartment);
    }

    @PutMapping("updateDepartment")
    public Departement updateDepartment(@RequestBody Departement d) {
        return ds.addDepartment(d);
    }

    @PutMapping("updateDepartments")
    public List<Departement> updateDepartment(@RequestBody List<Departement> listDepartment) {
        return ds.addDepartment(listDepartment);
    }

    @DeleteMapping("deleteDepartmentbyId")
    public void deleteDepartment(@RequestParam Long id) {
        ds.deleteDepartment(id);
    }

    @DeleteMapping("deleteDepartment")
    public void deleteDepartment(@RequestBody Departement d) {
        ds.deleteDepartment(d);
    }

    @GetMapping("findAllDepartments")
    public List<Departement> findAllDepartment() {
        return ds.findAllDepartment();
    }

    @GetMapping("findDepartmentById")
    public Departement findDepartmentById(@RequestParam Long id) {
        return ds.findDepartmentById(id);
    }
}
