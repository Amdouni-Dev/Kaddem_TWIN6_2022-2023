package esprit.tn.amdounidev;

import esprit.tn.amdounidev.Services.EquipeServiceImpl;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.mail.MessagingException;
import java.nio.file.Path;
import java.nio.file.Paths;

//@Configuration
////@EnableAutoConfiguration
//@EnableAspectJAutoProxy
////@ComponentScan
//
//
//
//@EnableScheduling
//@EnableBatchProcessing
//@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class KaddemApplication {
    // private static String userDirectory = System.getProperty("user.dir");
    public static void main(String[] args) {
        SpringApplication.run(KaddemApplication.class, args);
//        int i=0;
//        String filename="result.csv";
//        Path pathToFile = Paths.get(filename);
//        System.out.println(pathToFile.toAbsolutePath());
//        System.out.println("******************"+userDirectory);

    }


}
