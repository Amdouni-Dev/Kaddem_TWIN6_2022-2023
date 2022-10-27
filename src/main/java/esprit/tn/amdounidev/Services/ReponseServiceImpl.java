package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ReponseRepository;
import esprit.tn.amdounidev.entities.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseServiceImpl implements  ReponseService{

    @Autowired
    ReponseRepository tr;
    @Override
    public Reponse addReponse(Reponse r) {
        return  tr.save(r);
    }

    @Override
    public List<Reponse> addReponses(List<Reponse> listReponse) {
        return tr.saveAll(listReponse);
    }

    @Override
    public Reponse updateReponse(Reponse r) {
        return tr.save(r);
    }

    @Override
    public List<Reponse> updateReponses(List<Reponse> listReponse) {
        return tr.saveAll(listReponse);
    }

    @Override
    public void deleteReponse(Long id) {
        tr.deleteById(id);
    }

    @Override
    public void deleteReponse(Reponse r) {
        tr.delete(r);
    }

    @Override
    public List<Reponse> findAllReponse() {
        return tr.findAll();
    }

    @Override
    public Reponse findReponseById(Long id) {
        return tr.findById(id).orElse(new Reponse());
    }
}
