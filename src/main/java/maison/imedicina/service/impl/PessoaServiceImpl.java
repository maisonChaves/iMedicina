package maison.imedicina.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import maison.imedicina.dao.PessoaDao;
import maison.imedicina.file.PessoaFile;
import maison.imedicina.model.Pessoa;
import maison.imedicina.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaDao pessoaDao;

    @Autowired
    private PessoaFile pessoaFile;

    @Override
    public void create(Pessoa pessoa) {
        pessoaDao.create(pessoa);
    }

    @Override
    public Collection<Pessoa> upload(InputStream inputStream) throws FileNotFoundException, IOException {
        Collection<Pessoa> pessoas = pessoaFile.read(inputStream);
        Collection<Pessoa> inserted = new HashSet<>();
        for (Pessoa pessoa : pessoas) {
            boolean check = pessoaDao.checkExistence(pessoa);
            if(!check){
                inserted.add(pessoa);
                pessoaDao.create(pessoa);
            }
        }

        return inserted;

    }

    @Override
    public Collection<Pessoa> list() {
        return pessoaDao.list();
    }

    @Override
    public void update(Pessoa pessoa) {
        pessoaDao.update(pessoa);
    }

    @Override
    public Pessoa getPessoaById(long id) {
        return pessoaDao.getById(id);
    }

    @Override
    public void delete(long id) {
        pessoaDao.delete(id);
    }

}
