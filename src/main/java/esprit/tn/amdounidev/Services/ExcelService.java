package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    EquipeRepository repository;

    public ByteArrayInputStream load() {
        List<Equipe> equipes = repository.findAll();

        ByteArrayInputStream in = ExcelHelper.equipesToExcel(equipes);
        return in;
    }

}
