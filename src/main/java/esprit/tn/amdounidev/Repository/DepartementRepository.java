package esprit.tn.amdounidev.Repository;


import esprit.tn.amdounidev.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement,Long> {
}
