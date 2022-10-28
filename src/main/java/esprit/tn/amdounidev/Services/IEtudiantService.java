package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {
    Etudiant addEtudiant(Etudiant d);
    List<Etudiant> addEtudiant (List<Etudiant> listEtudiant);

    Etudiant updateEtudiant (Etudiant e);
    List<Etudiant> updateEtudiant (List<Etudiant> listEtudiant);

    void deleteEtudiant(Long id);
    void deleteEtudiant(Etudiant e);

    List<Etudiant> findAllEtudiant();
    Etudiant findEtudiantById (Long id);
}
