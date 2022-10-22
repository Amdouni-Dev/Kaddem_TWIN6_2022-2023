package esprit.tn.amdounidev.Services;


import esprit.tn.amdounidev.Repository.DepartementRepository;
import esprit.tn.amdounidev.entities.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService implements  IDepartementService{


    @Autowired //ou @Inject
    DepartementRepository dr;
    @Override
    public Departement addDepartment(Departement d) {
       return  dr.save(d);
    }

    @Override
    public List<Departement> addDepartment(List<Departement> listDepartment) {
        return dr.saveAll(listDepartment);
    }

    @Override
    public Departement updateDepartment(Departement d) {
        return dr.save(d);
    }

    @Override
    public List<Departement> updateDepartment(List<Departement> listDepartment) {
        return dr.saveAll(listDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
dr.deleteById(id);
    }

    @Override
    public void deleteDepartment(Departement d) {
dr.delete(d);
    }

    @Override
    public List<Departement> findAllDepartment() {
        return dr.findAll();
    }

    @Override
    public Departement findDepartmentById(Long id) {
        return dr.findById(id).orElse(new Departement());
    }
}
