package maison.imedicina.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import maison.imedicina.model.Pessoa;

public interface PessoaService {

    void create(Pessoa pessoa);

    Collection<Pessoa> list();

    void update(Pessoa pessoa);

    Pessoa getPessoaById(long id);

    void delete(long id);

    Collection<Pessoa> upload(InputStream inputStream) throws IOException;
}
