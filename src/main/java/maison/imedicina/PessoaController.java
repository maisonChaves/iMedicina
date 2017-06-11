package maison.imedicina;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import maison.imedicina.model.Pessoa;
import maison.imedicina.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Maison
 */
@RestController
@EnableAutoConfiguration
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);

    @GetMapping("/pessoa")
    public Collection<Pessoa> list(){
        return pessoaService.list();
    }
    
    @GetMapping("/pessoa/{id}")
    public Pessoa list(@PathVariable long id){
        return pessoaService.getPessoaById(id);
    }
    
    @PostMapping("/pessoa")
    public Pessoa create(@RequestBody Pessoa pessoa){
        pessoaService.create(pessoa);
        return pessoa;
    }
    
    @PutMapping("/pessoa/{id}")
    public Pessoa update(@PathVariable long id, @RequestBody Pessoa pessoa){
        pessoaService.update(pessoa);
        return pessoa;
    }
    
    @DeleteMapping("/pessoa/{id}")
    public void delete(@PathVariable long id){
        pessoaService.delete(id);
    }

    @PostMapping("/pessoa/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {

        LOGGER.debug("Single file upload!");

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            pessoaService.upload(uploadfile.getInputStream());
        } catch (IOException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " + uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }
}
