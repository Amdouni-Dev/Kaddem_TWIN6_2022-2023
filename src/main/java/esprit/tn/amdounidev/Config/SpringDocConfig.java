package esprit.tn.amdounidev.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI springShopOpenAPl() {
        return new  OpenAPI().info(infoaPl());
    }


    public Info infoaPl() {

        return new  Info().title("SpringDoc - Demo Saha loled").description("Aziz Ben Ismail - Eya ben Amor - Amen jouini - Yassmine mouadeb- aziz ben hmida - moune amdouni").contact(contactAPI());

    }

    public Contact contactAPI() {

        Contact contact = new  Contact().name("Equipe LPT").email("asssasiaisiigesprit.tn").url("https://mum.Linkedin.con/in/arsseeaess/");

        return contact;

    }
    @Bean
        public GroupedOpenApi All() {
        return GroupedOpenApi.builder()

                .group("All API")
                .pathsToMatch("/**")
                .build();

    }

    @Bean
    public GroupedOpenApi UniversitePublicApi() {
        return GroupedOpenApi.builder()

                .group("Only Universite Management API")
                .pathsToMatch("/Universite/**")
                .pathsToExclude("**")
                .build();

    }
    @Bean
    public GroupedOpenApi DepartementPublic() {
        return GroupedOpenApi.builder()

                .group("Only Product Departement API")
                .pathsToMatch("/Departement/**")
                .pathsToExclude("**")
                .build();

    }
    @Bean
    public GroupedOpenApi ContartPublic() {
        return GroupedOpenApi.builder()

                .group("Only Product Contart API")
                .pathsToMatch("/Contart/**")
                .pathsToExclude("**")
                .build();

    }
    @Bean
    public GroupedOpenApi DetailEquipePublic() {
        return GroupedOpenApi.builder()

                .group("Only Product DetailEquipe API")
                .pathsToMatch("/DetailEquipe/**")
                .pathsToExclude("**")
                .build();

    }
    @Bean
    public GroupedOpenApi equipePublic() {
        return GroupedOpenApi.builder()

                .group("Only Product equipe API")
                .pathsToMatch("/equipe/**")
                .pathsToExclude("**")
                .build();

    }
    @Bean
    public GroupedOpenApi EtudiantPublic() {
        return GroupedOpenApi.builder()

                .group("Only Product Etudiant API")
                .pathsToMatch("/Etudiant/**")
                .pathsToExclude("**")
                .build();

    }

    @Bean
    public GroupedOpenApi ProjetPublic() {
        return GroupedOpenApi.builder()

                .group("Only Product Projet API")
                .pathsToMatch("/Projet/**")
                .pathsToExclude("**")
                .build();

    }
    @Bean
    public GroupedOpenApi ReponsePublic() {
        return GroupedOpenApi.builder()

                .group("Only Product Reponse API")
                .pathsToMatch("/Reponse/**")
                .pathsToExclude("**")
                .build();

    }
    @Bean
    public GroupedOpenApi TachePublic() {
        return GroupedOpenApi.builder()

                .group("Only Product Tache API")
                .pathsToMatch("/Tache/**")
                .pathsToExclude("**")
                .build();

    }
    @Bean
    public GroupedOpenApi ThreadPublic() {
        return GroupedOpenApi.builder()

                .group("Only Product Thread API")
                .pathsToMatch("/Thread/**")
                .pathsToExclude("**")
                .build();

    }    @Bean
    public GroupedOpenApi ThreadTypePublic() {
        return GroupedOpenApi.builder()

                .group("Only Product ThreadType API")
                .pathsToMatch("/ThreadType/**")
                .pathsToExclude("**")
                .build();

    }
}