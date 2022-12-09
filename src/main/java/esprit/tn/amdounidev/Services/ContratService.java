package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Repository.ContratRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Repository.EtudiantRepository;
import esprit.tn.amdounidev.Repository.UniversiteRepository;
import esprit.tn.amdounidev.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    UniversiteRepository universiteRepository;

    @Override
    public Page<Contrat> retrieveAllContrats(Pageable pageable) {
        return contratRepository.findAll(pageable);
    }

    @Override
    public Contrat addContart(Contrat ce) {
        return contratRepository.save(ce);
    }

    @Override
    public Contrat updateContart(Long id,Contrat contrat) {
       contratRepository.save(contrat);
        return contrat;
    }

    @Override
    public Contrat retrieveContrat(Long idContrat) {
        return contratRepository.findById(idContrat).orElse(new Contrat());
    }

    @Override
    public void removeContratById(Long idContrat) {
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
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Long idContrat, Long idEquipe) {
        //Etudiant (Slave) -- Contrat (Master)
        //Etudiant (Slave) -- Equipe (Master)

        Contrat contrat = contratRepository.findById(idContrat).get();
        Equipe equipe = equipeRepository.findById(idEquipe).get();
      //  e=etudiantRepository.save(e);
        contrat.setEtudiant(e);
        contratRepository.save(contrat);
        equipe.getEtudiants().add(e);
        equipeRepository.save(equipe);
        return e;
    }

    @Override
    public Float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate, Long idUniversite) {
        float montant=0;
        Universite universite=universiteRepository.findById(idUniversite).get();
        for (Departement d: universite.getDepartments()){
            for (Etudiant e:d.getEtudiants()){
                for(Contrat c:e.getContrats()){
                    montant+=c.getMontantC();
                    if(c.getArchive()==false){
                    if(startDate.after(c.getDateDebutC()) && endDate.after(c.getDateFinC())){
                        if (c.getSpecailite()==Specailite.IA){
                            montant=montant+300;
                        }else if (c.getSpecailite()==Specailite.RESEAU){
                            montant=montant+350;
                        }else if (c.getSpecailite()==Specailite.CLOUD){
                            montant=montant+400;
                        }else if(c.getSpecailite()==Specailite.SECURITE){
                            montant=montant+450;
                        }
                    }
                }}
            }
        }
        return montant;
    }


    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String nomE, String prenomE) {
        Etudiant e = contratRepository.EtudiantByNomAndPrenom(nomE,prenomE);
        log.info("11111111111111yyyyyyyyyyyyyyyyyyyyyyyyyyyy",e.getNom());
     //   e.getContrats();
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

    @Override
    public List<Contrat> contratsArchives() {
        return contratRepository.nbrContrat();
    }

    @Override
    public List<Contrat> contratsNonArchives() {
        return contratRepository.contratsNonArchives();
    }

    public long calculDiff(Date date2) {

        long DiffInMillies = new Date().getTime() - date2.getTime();
        return TimeUnit.DAYS.convert(DiffInMillies, TimeUnit.MILLISECONDS);
    }
}


