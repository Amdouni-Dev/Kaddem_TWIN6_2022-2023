package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ReponseRepository;
import esprit.tn.amdounidev.entities.Reponse;
import esprit.tn.amdounidev.entities.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@Service
public class ReponseServiceImpl implements  ReponseService{

    @Autowired
    ReponseRepository tr;
    @Autowired
    ThreadServiceImpl ts;


    @Override
    public List<Reponse> findByThread(long id) {
        return tr.findByThread(id) ;
    }

    @Override
    public Reponse addReponse(Reponse r) {
        r.setNb_likes(0);
        r.setReplydate(LocalDateTime.now());
        r.setUpdatedAt(LocalDateTime.now());
        r=switcher(r);
        return  tr.save(r);
    }

    @Override
    public List<Reponse> addReponses(List<Reponse> listReponse) {
        return tr.saveAll(listReponse);
    }

    @Override
    public Reponse updateReponse(Reponse r) {

        r.setUpdatedAt(LocalDateTime.now());
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

    public String[] mots(){
        String[] l;
        l=ts.findThreadById((long)99).getObject().split(" ");
        return l;
    }
    public boolean detector(String s1,String s2) {
        if(s1.equals(s2)) {

            return true;
        }

        return false;

    }


    public Reponse switcher(Reponse m) {
        String[] tokens=m.getReply().split(" ");
        String text = "";
        for(String s:tokens) {

            String[] mot=mots();
            String test=s;
            for(String ss:mot) {
                if(detector(s,ss)) {
                    test="****";
                }
            }
            text=text+" "+test;
        }

        m.setReply(text);
        return m;
    }
}



