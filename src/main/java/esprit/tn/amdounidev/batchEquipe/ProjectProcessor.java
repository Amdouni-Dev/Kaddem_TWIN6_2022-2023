package esprit.tn.amdounidev.batchEquipe;

import esprit.tn.amdounidev.entities.Equipe;
import esprit.tn.amdounidev.entities.Etudiant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;


@Slf4j
public class ProjectProcessor implements ItemProcessor<Equipe, Equipe> {
	/*12. logique métier de notre job*/
	@Override
	public Equipe process(Equipe equipe) {
		log.info("Start Batch Item Processor");
		for (Etudiant etudiant : equipe.getEtudiants() ) {
			etudiant.getContrats().forEach(contrat -> {
				//todo3  (mettre à jour le montant du contrat avec une augmentation de 10%)
				contrat.setMontantC(contrat.getMontantC()+ (contrat.getMontantC()*10/100));

			});
		}
		return equipe;
	}
}
