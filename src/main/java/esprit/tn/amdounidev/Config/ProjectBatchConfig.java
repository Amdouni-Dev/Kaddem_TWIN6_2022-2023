package esprit.tn.amdounidev.Config;

/*
import esprit.tn.amdounidev.BatchProjet.ProjectProcessorProjet;
import esprit.tn.amdounidev.BatchProjet.ProjectReaderProjet;
import esprit.tn.amdounidev.BatchProjet.ProjectWriterProjet;
import esprit.tn.amdounidev.entities.Projet;
 */

import esprit.tn.amdounidev.batchEquipe.ProjectProcessor;
import esprit.tn.amdounidev.batchEquipe.ProjectReader;
import esprit.tn.amdounidev.batchEquipe.ProjectWriter;
import esprit.tn.amdounidev.entities.Equipe;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;

@Configuration
/*3. Activer le traitement par lot */
/*todo8*/
@Slf4j
@AllArgsConstructor
public class ProjectBatchConfig {

	/*4. Création des variables de notre batch (nom job, nom step) */
	private static final String JOB_NAME = "listProjectJob";
	private static final String STEP_NAME = "processingStep";

	private JobBuilderFactory jobBuilderFactory;

	private StepBuilderFactory stepBuilderFactory;


	/*5 Créer le bean associé au job et le lancer */
	@Bean
	public Job listProjectsJob(Step step1) {

		return jobBuilderFactory.get(JOB_NAME).start(step1).build();
	}

	/*6 Créer le step associé au job et le lancer */
	@Bean
	public Step projectStep() {

		try {
			return stepBuilderFactory.get(STEP_NAME)
					.<Equipe, Equipe>chunk(2).reader(projectItemReader().read())
					.processor(projectItemProcessor()).writer(projectItemWriter())
					.exceptionHandler((context, throwable) -> log.error("Skipping record on file. cause="+ throwable.getCause()))
					.build();
		} catch (Exception e) {
			log.error("End Batch Step");
			return stepBuilderFactory.get(STEP_NAME).chunk(2).build();
		}

	}
	



	/* 7. étape 1 (ItemReader) fait appel à la classe ProjectReader
	 * qui se charge de modifier la logique des données selon
	 * nos besoins métiers */


	@Bean
	public ProjectReader projectItemReader() {
		return new ProjectReader();
	}
	/* 8. étape 2 (ItemProcessor) fait appel à la classe ProjectProcessor
	 * qui se charge de modifier la logique des données selon
	 * nos besoins métiers */
	@Bean
	public ItemProcessor<Equipe, Equipe> projectItemProcessor() {
		return new ProjectProcessor();
	}


	/* 9. étape 3 (ItemWriter) fait appel à la classe ProjectWriter
	 * qui se charge de lancer le service de sauvegarde des
	 * données liées à la partie Equipe dans la BD*/
	@Bean
	public ItemWriter<Equipe> projectItemWriter() {
		return new ProjectWriter();
	}



	//----------------------------------------------------------------------------------batch eya
	/*

	@Bean
	public Step projectStep() {

		try {
			return stepBuilderFactory.get(STEP_NAME)
					.<Projet, Projet>chunk(2).reader(projectItemReader().read())
					.processor(projectItemProcessor()).writer(projectItemWriter())
					.exceptionHandler((context, throwable) -> log.error("Skipping record on file. cause="+ throwable.getCause()))
					.build();
		} catch (Exception e) {
			log.error("End Batch Step");
			return stepBuilderFactory.get(STEP_NAME).chunk(2).build();
		}

	}






	@Bean
	public ProjectReaderProjet projectItemReader() {
		return new ProjectReaderProjet();
	}

	@Bean
	public ItemProcessor<Projet, Projet> projectItemProcessor() {
		return new ProjectProcessorProjet();
	}

	@Bean
	public ItemWriter<Projet> projectItemWriter() {
		return new ProjectWriterProjet();
	}
	 */





}
