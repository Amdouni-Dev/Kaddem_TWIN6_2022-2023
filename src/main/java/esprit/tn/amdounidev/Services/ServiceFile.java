package esprit.tn.amdounidev.Services;



import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServiceFile {

    private String rootPath =  "\\src\\main\\resources\\PhotosEquipe";
    private static final String userDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";

    private String generateRandomName() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public String saveFile(MultipartFile file, String subPath) throws IOException {
//String folder="/Photos/";
//byte[] bytes=file.getBytes();
//Path path2= Paths.get(userDirectory+file.getOriginalFilename());
//Files.write(path2,bytes);
       String fileName = generateRandomName() + "." + (file.getContentType().split("/"))[1];
     //   String fileName = file.getOriginalFilename() ;
        String completePath = userDirectory + subPath;
        Path path = Paths.get(completePath);

        System.out.println("000000000000000000000000000"+fileName);
        System.out.println("111111111111111111111111111"+completePath);
        try {
            Files.copy(file.getInputStream(), path.resolve(fileName));

        } catch (Exception exception) {
            System.out.println("errur lors de chargement image:: " + exception.getMessage());

        }
        return fileName;

    }

    public void deleteFile(String subPath) {
        String completePath = rootPath + subPath;

        Path path = Paths.get(completePath);
        try {
            Files.delete(path);

        } catch (Exception exception) {
            System.out.println("error while uploading image catch:: " + exception.getMessage());
        }
    }
}
