package esprit.tn.amdounidev.Services;


import esprit.tn.amdounidev.Repository.DepartementRepository;
import esprit.tn.amdounidev.Repository.UniversiteRepository;
import esprit.tn.amdounidev.entities.Departement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartementService implements  IDepartementService{

    @Autowired //ou @Inject
    DepartementRepository dr;

    @Autowired //ou @Injectl
    UniversiteRepository UR;

    @Override
    public Departement addDepartment(Departement departement) {
        log.info("universite ajouté :\n" +
                "Nom d\'Departement "+departement.getIdDepartement() +" " +
                "ID d\'Departement " +departement.getNomDepartement()+"" );
       return  dr.save(departement);

    }

    @Override
    public List<Departement> addDepartment(List<Departement> Departments) {
        log.info("Une Liste d'Department a ete Ajouter");
        return dr.saveAll(Departments);
    }

    @Override
    public Departement updateDepartment(Departement departement, long id) {
        departement.setIdDepartement(id);
        log.info("modification d'Une departement");
        return dr.save(departement);
    }

    @Override
    public List<Departement> updateDepartments(List<Departement> listDepartment) {
        log.info("modification de Une Liste d'departements");
        return dr.saveAll(listDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        log.info("suppression d'Une departements par id");
        dr.deleteById(id);
    }

    @Override
    public void deleteDepartment(Departement departement) {
        log.info("suppression d'Une departement specifique");
        dr.delete(departement);
    }

    @Override
    public List<Departement> findAllDepartment() {
        log.info("récuperation de tous les departement");

        return dr.findAll();
    }

    @Override
    public Departement findDepartmentById(Long id) {
        log.info("récuperation d'un departement par ID");
        return dr.findById(id).orElse(new Departement());
    }
}
