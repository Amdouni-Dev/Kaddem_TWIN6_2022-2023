package esprit.tn.amdounidev.Services;



import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import esprit.tn.amdounidev.controllers.TacheController;
import esprit.tn.amdounidev.entities.Notee;
import esprit.tn.amdounidev.entities.Tache;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FirebaseService {

  /*  private final FirebaseMessaging firebaseMessaging;

    public FirebaseService(FirebaseMessaging firebaseMessaging) {
   this.firebaseMessaging = firebaseMessaging;
    }



    public String sendNotification(Notee note) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        Message message = Message
                .builder()
                .setTopic("kaddem-287d6")
                .setNotification(notification)
                .putAllData(note.getData())
                .build();

        return firebaseMessaging.send(message);
    }
*/
}






