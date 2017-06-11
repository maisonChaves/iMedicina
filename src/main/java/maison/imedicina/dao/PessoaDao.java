package maison.imedicina.dao;

import java.util.Collection;
import maison.imedicina.model.Pessoa;

public interface PessoaDao {

    void create(Pessoa pessoa);

    void update(Pessoa pessoa);

    Pessoa getPessoaById(long id);

    Pessoa getPessoaByName(String name);

    void delete(long id);

    Collection<Pessoa> list();
}
