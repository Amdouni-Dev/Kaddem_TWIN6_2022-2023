package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.entities.DetailEquipe;

import java.util.List;

public interface DetailEquipeService {
    DetailEquipe addDetailEquipe(DetailEquipe d);
    List<DetailEquipe> addDetailEquipe (List<DetailEquipe> listDetailEquipe);

    DetailEquipe updateDetailEquipe (DetailEquipe d);
    List<DetailEquipe> updateDetailEquipe (List<DetailEquipe> listDetailEquipe);

    void deleteDetailEquipe(Long id);
    void deleteDetailEquipe(DetailEquipe d);

    List<DetailEquipe> findAllDetailEquipe();
    DetailEquipe findDetailEquipeById (Long id);
}
