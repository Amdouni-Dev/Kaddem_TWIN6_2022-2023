package esprit.tn.amdounidev.BatchProjet;
import esprit.tn.amdounidev.Services.IProjetService;
import esprit.tn.amdounidev.entities.Projet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;


@Slf4j
public class ProjectReaderProjet implements ItemReader<ItemReader<Projet>> {
    @Autowired
    private IProjetService projetService;


        private static final String FILE_NAME = "projetTache.csv";
        private static final String READER_NAME = "projectItemReader";


        private String names = "" ; // todo4



        private String delimiter = ",";



        @Override
        public ItemReader<Projet> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
            log.error("Start Batch Item Reader");
            FlatFileItemReader<Projet> reader = new FlatFileItemReader<>();
            reader.setResource(new ClassPathResource(FILE_NAME));
            reader.setName(READER_NAME);
            reader.setLinesToSkip(1);
            /*11. récupérer les données ligne par ligne du fichier excel */
            reader.setLineMapper(projectLineMapper());

            return reader;
        }


        public LineMapper<Projet> projectLineMapper() {

            log.info("Start Batch Line Mapper");

            final DefaultLineMapper<Projet> defaultLineMapper = new DefaultLineMapper<>();
            final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
            lineTokenizer.setDelimiter(delimiter);
            lineTokenizer.setStrict(false);
            lineTokenizer.setNames(names.split(delimiter));
            defaultLineMapper.setLineTokenizer(lineTokenizer);
            defaultLineMapper.setFieldSetMapper(fieldSet -> {
                if (fieldSet.readBoolean(2)) { // on a récupérer le parametre terminated du fichier projet.csv et verifier que sa valeur est true
                    return projetService.retrieveProjet( (long) fieldSet.readInt(1));
                }
                else {
                    return null;
                }
            });
            return defaultLineMapper;
        }
}
