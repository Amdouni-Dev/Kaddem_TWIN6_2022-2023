package esprit.tn.amdounidev.entities;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@Component
public class FirebaseBean {
/*
    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {

        FileInputStream refreshToken = new FileInputStream("C:/Users/MSI/Documents/FirebaseKey/kaddem-287d6-firebase-adminsdk-7ggel-442f28a00b.json");

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(refreshToken);
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
        return FirebaseMessaging.getInstance(app);
    }
*/
}

