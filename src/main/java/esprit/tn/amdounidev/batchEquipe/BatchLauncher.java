package esprit.tn.amdounidev.batchEquipe;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
/*todo1*/
public class BatchLauncher {

	private JobLauncher jobLauncher;
	private Job job;

	/*2. Lancer le job lancher  avec le job (ou la liste de job à éxécuter) */
	public BatchStatus run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {
		JobParameters parameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, parameters);
		return jobExecution.getStatus();
	}
}
