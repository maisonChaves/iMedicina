package maison.imedicina.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import maison.imedicina.model.Pessoa;

/**
 *
 * @author Maison Chaves
 */
public interface PessoaFile {
    public Collection<Pessoa> read(InputStream myFile) throws FileNotFoundException, IOException ;
}
