package maison.imedicina.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import maison.imedicina.model.Pessoa;

/**
 * Service de Pessoa.
 *
 * @author Maison Chaves
 */
public interface PessoaService {

    /**
     * Persiste uma pessoa.
     *
     * @param pessoa pessoa a ser persistida.
     * @author Maison Chaves
     */
    void create(Pessoa pessoa);

    Collection<Pessoa> list();

    void update(Pessoa pessoa);

    Pessoa getPessoaById(long id);

    void delete(long id);

    Collection<Pessoa> upload(InputStream inputStream) throws IOException;
}
