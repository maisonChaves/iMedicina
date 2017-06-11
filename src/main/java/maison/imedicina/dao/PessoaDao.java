package maison.imedicina.dao;

import java.util.Collection;
import maison.imedicina.model.Pessoa;

public interface PessoaDao {

    void create(Pessoa pessoa);

    void update(Pessoa pessoa);

    Pessoa getById(long id);

    boolean checkExistence(Pessoa pessoa);

    void delete(long id);

    Collection<Pessoa> list();
}
