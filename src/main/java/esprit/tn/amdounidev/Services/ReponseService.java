package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.Reponse;

import java.util.List;

public interface ReponseService {

    List<Reponse> findByThread(long id);

    Reponse addReponse(Reponse d);
    List<Reponse> addReponses (List<Reponse> listReponse);

    Reponse updateReponse (Reponse d);
    List<Reponse> updateReponses (List<Reponse> listReponse);

    void deleteReponse(Long id);
    void deleteReponse(Reponse d);

    List<Reponse> findAllReponse();
    Reponse findReponseById (Long id);

}
