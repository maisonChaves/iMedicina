package maison.imedicina.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import maison.imedicina.model.Pessoa;

/**
 * Realiza tratamento de arquivos de Pessoa.
 *
 * @author Maison Chaves
 */
public interface PessoaFile {
    /**
     * Converte um arquivo em uma coleção de Pessoas.
     * @param pessoaFile aquivo com entrada de pesosas.
     * @return Lista de pessoas do arquivo.
     * @throws IOException Quando existe erro na leitura e conversão do aquivo
     */
    Collection<Pessoa> read(InputStream pessoaFile) throws IOException ;
}
