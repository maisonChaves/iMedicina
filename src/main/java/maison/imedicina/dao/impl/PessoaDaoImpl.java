package maison.imedicina.dao.impl;

import java.util.Collection;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import maison.imedicina.dao.PessoaDao;
import maison.imedicina.model.Pessoa;

@Repository
public class PessoaDaoImpl implements PessoaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Pessoa pessoa) {
        entityManager.persist(pessoa);
    }

    @Override
    public void update(Pessoa pessoa) {
        entityManager.merge(pessoa);
    }

    @Override
    public Pessoa getById(long id) {
        return entityManager.find(Pessoa.class, id);
    }

    @Override
    public boolean checkExistence(Pessoa pessoa) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Pessoa> query = cb.createQuery(Pessoa.class);

        ParameterExpression<String> parameter = cb.parameter(String.class, "nome");

        Root<Pessoa> pessoas = query.from(Pessoa.class);

        query.where(cb.like(pessoas.get("nome"), parameter));

        return entityManager
                .createQuery(query)
                .setParameter(parameter, pessoa.getNome())
                .getSingleResult() != null;
    }

    @Override
    public void delete(long id) {
        Pessoa pessoa = getById(id);
        if (pessoa != null) {
            entityManager.remove(pessoa);
        }
    }

    @Override
    public Collection<Pessoa> list() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Pessoa> query = cb.createQuery(Pessoa.class);

        Root<Pessoa> c = query.from(Pessoa.class);
        query.select(c);

        return entityManager.createQuery(query).getResultList();
    }

}
