package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.entities.DetailEquipe;
import esprit.tn.amdounidev.entities.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EquipeServiceImpl implements EquipeService {
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    DetailEquipeRepository detailEquipeRepository ;


    @Override
    public void saveEquipe(Equipe equipe) {
       DetailEquipe detailEquipe=new DetailEquipe();

       detailEquipe.setDateCreation(new Date());
       detailEquipe.setDateActivation(new Date());
        detailEquipe.setDateSuppression(new Date());
       detailEquipeRepository.save(detailEquipe);
        equipe.setDetailEquipe(detailEquipe);
       equipe.setIsValid(false);
       equipe.setIsDeleted(false);

 equipeRepository.save(equipe);
    }

    @Override
    public void saveEquipeAndDetail(Equipe equipe, DetailEquipe detailEquipe) {


        detailEquipe.setDateCreation(new Date());
        detailEquipe.setDateActivation(new Date());
        detailEquipe.setDateSuppression(new Date());
        detailEquipeRepository.save(detailEquipe);
        equipe.setDetailEquipe(detailEquipe);
        equipe.setIsValid(false);
        equipe.setIsDeleted(false);

        equipeRepository.save(equipe);
    }


    @Override
    public Equipe updateEquipe(Equipe equipe, Long idE) {
      //  Equipe updateEquipe = equipeRepository.findById(idE).orElseThrow(()-> new IllegalArgumentException("Not Found"));
        Optional<Equipe> updateEquipe = equipeRepository.findById(idE);
        if (updateEquipe.isPresent()) {
        equipe.setNomEquipe  (equipe.getNomEquipe());
            equipe.setNiveau(equipe.getNiveau());
            equipe.setIsValid(false);
            equipe.setIsDeleted(false);



        equipeRepository.save(equipe);}
        return equipe;
    }








    /*
    * @PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());

		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
    * */


    @Override
    public void deleteEquipe(Equipe equipe) {
        equipeRepository.delete(equipe);

    }
    

    @Override
    public void deleteEquipeById(Long id) {
        equipeRepository.deleteById(id);
    }

    @Override
    public List<Equipe> findEquipes() {



        return equipeRepository.findAllEquipes();
    }

    @Override
    public Optional<Equipe> findById(Long id) {
        return equipeRepository.findById(id);
    }

    @Override
    public Boolean isValid(Equipe e,Long id) {
        if( e.getIdEquipe()==id && e.getIsValid()==true){
            return true;
        }
        else return false;
    }

    @Override
    public Boolean changeIsValid(Equipe e) {



        if(e.getIsValid()==true){
            e.setIsValid(false);
            return true;
        }
        else e.setIsValid(true);
        return false;
    }


    public Equipe addE(Equipe e) {
        return equipeRepository.save(e);
    }


}
