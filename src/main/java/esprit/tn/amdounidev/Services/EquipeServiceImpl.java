package esprit.tn.amdounidev.Services;

import esprit.tn.amdounidev.Exceptions.EquipeNotFoundException;
import esprit.tn.amdounidev.Repository.DetailEquipeRepository;
import esprit.tn.amdounidev.Repository.EquipeRepository;
import esprit.tn.amdounidev.Repository.EtudiantRepository;
import esprit.tn.amdounidev.Repository.ImageEquipeRepo;
import esprit.tn.amdounidev.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EquipeServiceImpl implements EquipeService {
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    DetailEquipeRepository detailEquipeRepository ;
    @Autowired
    ImageEquipeRepo imr;

    @Override
    public EquipeResponse getAllEquipes(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Equipe> equipes = equipeRepository.findAll(pageable);

        // get content for page object
        List<Equipe> listOfEquipes = equipes.getContent();

        List<Equipe> content= listOfEquipes.stream().map(equipe -> mapToDTO(equipe)).collect(Collectors.toList());

        EquipeResponse postResponse = new EquipeResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(equipes.getNumber());
        postResponse.setPageSize(equipes.getSize());
        postResponse.setTotalElements(equipes.getTotalElements());
        postResponse.setTotalPages(equipes.getTotalPages());
        postResponse.setLast(equipes.isLast());

        return postResponse;
    }
    private Equipe mapToDTO(Equipe equipe){
        Equipe e = new Equipe();
        e.setIdEquipe(equipe.getIdEquipe());
        e.setNomEquipe(equipe.getNomEquipe());

        return e;
    }

    @Override
    public Equipe saveEquipe(Equipe equipe) throws IOException {

      //  equipeRepository.save(equipe);
/***********          Details**/
       DetailEquipe detailEquipe=equipe.getDetailEquipe();

       detailEquipe.setDateCreation(new Date());
       detailEquipe.setDateActivation(new Date());
        detailEquipe.setDateSuppression(new Date());
        /***********          Image**/


       //  detailEquipeRepository.save(detailEquipe);
       // equipe.setDetailEquipe(detailEquipe);
       equipe.setIsValid(false);
       equipe.setIsDeleted(false);
        detailEquipeRepository.save(equipe.getDetailEquipe());

     //   log.info("Original Image Byte Size - " + equipe.getImage().getBytes().length);
       // equipe.setImage(Image.b);

 equipeRepository.save(equipe);

 log.info("Equipe ajouté :\n" +
         "Nom d\'equipe "+equipe.getNomEquipe() +" " +
         "Niveau " +equipe.getNiveau()+" "+
         "Etat de suppression : non supprimé" +
         "Etat de validation : non validé " +
         "Etat Ajouté par defaut(false)" +
         "Niveau par defaut junior ! ." );
 return equipe;
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
    public Equipe findById(Long id) {
        return equipeRepository.findById(id).orElseThrow(() -> new EquipeNotFoundException(id));
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

    @Override
    public List<Etudiant> getMembers(Long id) {
        return (List<Etudiant>) equipeRepository.getMembers(id);
    }

    @Override
    public void faireEvoluerEquipes() {

    }

    @Override
    public void MembreToResponsableEquipe( Long idEt,Long idEquipe) {
        Equipe eq=equipeRepository.findById(idEquipe).get();
        Etudiant etudiant=etudiantRepository.findById(idEt).get();
        etudiant.setRoleEtudiantEquipe(RoleEtudiantEquipe.RESPONSABLE);
        etudiantRepository.save(etudiant);

        eq.setEtudiant(etudiant);
        equipeRepository.save(eq);
//        if(etudiant.getRoleEtudiantEquipe()==RoleEtudiantEquipe.MEMBRE ) {
//
//            etudiant.setRoleEtudiantEquipe(RoleEtudiantEquipe.RESPONSABLE);
//            etudiantRepository.save(etudiant);
//        }
    }

    @Override
    public void supprimerEtudiantFromEquipe(Long idEt, Long idEquipe) {
        Etudiant etudiant=etudiantRepository.findById(idEt).get();
        Equipe eq=equipeRepository.findById(idEquipe).get();
         List<Etudiant> listEtudiant=eq.getEtudiants();
         listEtudiant.remove(etudiant);
         etudiant.setRoleEtudiantEquipe(null);

         etudiantRepository.save(etudiant);

    }

    @Override
    public void ajouterMembreEquipe(Long idEq, Long idEt) {

    Etudiant etudiant=etudiantRepository.findById(idEt).get();

        Equipe equipe=equipeRepository.findById(idEq).get();

if(equipe.getEtudiants().size()<equipe.getDetailEquipe().getNombreParticipants() && etudiant.getRoleEtudiantEquipe()!=RoleEtudiantEquipe.RESPONSABLE) {

    equipe.getEtudiants().add(etudiant);
    etudiant.setDateEntreeEquipe(new Date());
    equipeRepository.save(equipe);
    if(etudiant.getRoleEtudiantEquipe()==null ) {

        etudiant.setRoleEtudiantEquipe(RoleEtudiantEquipe.MEMBRE);
        etudiant.setIsDeletedFromEquipe(false);
        etudiantRepository.save(etudiant);
    }
}

else{
    log.info("il faut pas depasser " +equipe.getDetailEquipe().getNombreParticipants()+ " membre dans l'equipe");
    log.info("le responsable doit rejoindre unje seule equipe, L'etudiant nommé : ` "+etudiant.getNom()+" ` est deja responsable d'une equipe");
}

// if Role== null

   // if Role==Membre-->Responsable




    }


    public Etudiant modifierRoleEtudiant(Long idEt){
        Etudiant etudiant=etudiantRepository.findById(idEt).get();
        etudiant.setRoleEtudiantEquipe(RoleEtudiantEquipe.MEMBRE);
        etudiantRepository.save(etudiant);
        return etudiant;
    }

    public Equipe addE(Equipe e) {
        return equipeRepository.save(e);
    }

    @Override
    public void affecterImageToEquipe(Long idE, Long idIm) {
        Equipe e=equipeRepository.findById(idE).get();
        ImageEquipe im=imr.findById(idIm).get();
        e.setImageEquipe(im);
        equipeRepository.save(e);
    }


    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mounaamdouni213@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");


    }

    @Override
    public Etudiant deleteEtudiantFromEquipe(Long idEtudiant) {
        Etudiant et=etudiantRepository.findById(idEtudiant).get();
        et.setDateSortieEquipe(new Date());
        et.setIsDeletedFromEquipe(true);
        return etudiantRepository.save(et);

    }

    @Override
    public Etudiant ValiderEtudiant(Long idEtudiant) {
        Etudiant et=etudiantRepository.findById(idEtudiant).get();
        et.setDateEntreeEquipe(new Date());
        et.setIsDeletedFromEquipe(false);
        return etudiantRepository.save(et);
    }
    public Page<Equipe> findAll(Pageable pageable) {
        return equipeRepository.findAll(pageable);
    }
    public Equipe retrieveEquipe(Long equipeId){
        return equipeRepository.findById(equipeId).get();
    }

}
