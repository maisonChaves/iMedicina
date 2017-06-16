package maison.imedicina;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import maison.imedicina.model.Pessoa;
import maison.imedicina.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller de Pessoa.
 *
 * @author Maison Chaves
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
    public Pessoa get(@PathVariable long id){
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
    public Collection<Pessoa> uploadFile(@RequestParam("file") MultipartFile uploadfile) {

        Collection<Pessoa> pessoas;

        if (uploadfile.isEmpty()) {
            return Collections.emptySet();
        }

        try {
           pessoas = pessoaService.upload(uploadfile.getInputStream());
        } catch (IOException ex) {
            return Collections.emptySet();
        }

        return pessoas;

    }
}
