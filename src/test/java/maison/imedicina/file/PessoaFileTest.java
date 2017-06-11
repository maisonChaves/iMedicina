
package maison.imedicina.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import maison.imedicina.file.impl.PessoaFileImpl;
import maison.imedicina.model.Pessoa;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maison Chaves
 */
public class PessoaFileTest {
    
    public PessoaFileTest() {
    }
    
    /**
     * Test of read method, of class PessoaFile.
     * @throws java.lang.Exception
     */
    @Test
    public void testReadFile() throws Exception {
        System.out.println("readFile");
        PessoaFile pessoaFile = new PessoaFileImpl();
        Collection<Pessoa> pessoas = pessoaFile.read(new FileInputStream(new File("D:\\imedicina\\pessoas.xlsx")));
        
        System.out.println("Size: " + pessoas.size());
        assertThat(pessoas, is(not(empty())));
        
        for (Pessoa pessoa : pessoas) {
            assertThat(pessoa.getNome(), not(isEmptyOrNullString()));
            System.out.print("Nome: " + pessoa.getNome());
            System.out.print(",\t Telefone: " + pessoa.getTelefone());
            System.out.print(",\t Email: " + pessoa.getEmail());
            System.out.println(",\t Data: " + pessoa.getDataNascimento());
        }
        
    }
    
}
