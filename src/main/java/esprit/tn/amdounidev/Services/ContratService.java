package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ContratRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Repository.EtudiantRepository;
import esprit.tn.amdounidev.entities.Contrat;
import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Etudiant;
import esprit.tn.amdounidev.entities.Specailite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ContratService implements IContratService {

    // @Inject
    @Autowired
    ContratRepository contratRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    EquipeRepository equipeRepository;


    @Override
    public List<Contrat> retrieveAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat addContart(Contrat ce) {
        return contratRepository.save(ce);
    }

    @Override
    public Contrat updateContart(Contrat contrat, Long id) {
        Optional<Contrat> updateContrat = contratRepository.findById(id);
        if (updateContrat.isPresent()) {
            Long idCtrt = contrat.getIdContrat();
            System.out.println(idCtrt);
            contrat.setArchive(contrat.getArchive());
            contrat.setDateDebutC(contrat.getDateDebutC());
            contrat.setDateFinC(contrat.getDateFinC());
            contrat.setMontantC(contrat.getMontantC());
            contrat.setSpecailite(contrat.getSpecailite());
            contratRepository.save(contrat);
        }
        return contrat;
    }

    @Override
    public Contrat retrieveContrat(Long idContrat) {
        return contratRepository.findById(idContrat).orElse(new Contrat());
    }

    @Override
    public void removeContrat(Long idContrat) {
        contratRepository.deleteById(idContrat);
    }

    @Override
    public void removeContrat(Contrat ce) {
        contratRepository.delete(ce);
    }

    @Override
    public Integer nbContratsValides(Date startDate, Date endDate) {
        int nbCV=0;
        if (startDate.before(endDate)) {
            for (Contrat c:contratRepository.nbrContrat()){
                if(c.getDateDebutC().before(startDate) | c.getDateDebutC().equals(startDate)
                        && c.getDateFinC().after(endDate) | c.getDateFinC().equals(endDate)){
                    nbCV++;
                }
            }   log.info("nbCV="+nbCV);

        } else {
            log.info("Date is invalid !");
        }
        return nbCV ;
    }

    @Override
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
        //Etudiant (Slave) -- Contrat (Master)
        //Etudiant (Slave) -- Equipe (Master)
        Contrat contrat = contratRepository.findById(Long.valueOf(idContrat)).get();
        Equipe equipe = equipeRepository.findById(Long.valueOf(idEquipe)).get();
        e=etudiantRepository.save(e);
        contrat.setEtudiant(e);
        contratRepository.save(contrat);
        equipe.getEtudiants().add(e);
        equipeRepository.save(equipe);
        return e;
    }

    @Override
    public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {


        return 0;
    }

    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String nomE, String prenomE) {
        Etudiant e = contratRepository.EtudiantByNomAndPrenom(nomE,prenomE);
        e.getContrats();
        log.info("hello"+e.getContrats());
        if(e.getContrats().size()<5){
            e.getContrats().add(ce);
            etudiantRepository.save(e);
            log.info("Well done !");
        }else {
            log.info("Vous avez dépassé la limite");
        }
        return null;
    }

    @Override
    public String retrieveAndUpdateStatusContrat() {
        String msg = "";
        for (Contrat c : contratRepository.findAll()) {
            long diff = calculDiff(c.getDateFinC());
            if (diff == 0) {
                c.setArchive(true);
                Contrat contrat = contratRepository.save(c);
                msg = ("Contrat archive:" + contrat.getEtudiant().getNom() + "" + contrat.getEtudiant().getPrenom()) + "\n";
            } else if (diff <= 15) {
                msg = c.getDateFinC() + "Specialite" + c.getSpecailite() + "" + c.getEtudiant().getNom() + c.getEtudiant().getPrenom() +
                        "\n";
            }
        }
        return null;
    }

    public long calculDiff(Date date2) {

        long DiffInMillies = new Date().getTime() - date2.getTime();
        return TimeUnit.DAYS.convert(DiffInMillies, TimeUnit.MILLISECONDS);
    }
}

