package esprit.tn.amdounidev.Exceptions;

public class EquipeNotFoundException extends   RuntimeException {

    public EquipeNotFoundException(Long id) {
        super("Equipe introuvable " + id);
    }
}
