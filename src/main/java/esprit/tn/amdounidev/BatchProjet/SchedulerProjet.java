package esprit.tn.amdounidev.BatchProjet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class SchedulerProjet {

    private BatchLauncherProjet batchLauncher;



    public void perform() throws Exception {

        log.info("Batch programmé pour tourner toutes les 5 minutes");
        batchLauncher.run();
    }
}
