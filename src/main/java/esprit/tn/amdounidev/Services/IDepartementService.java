package esprit.tn.amdounidev.Services;


import esprit.tn.amdounidev.entities.Departement;

import java.util.List;

public interface IDepartementService {
    Departement addDepartment(Departement d);
    List<Departement> addDepartment (List<Departement> listDepartment);

    Departement updateDepartment (Departement d);
    List<Departement> updateDepartments (List<Departement> listDepartment);

    void deleteDepartment(Long id);
    void deleteDepartment(Departement d);

    List<Departement> findAllDepartment();
    Departement findDepartmentById (Long id);
}
