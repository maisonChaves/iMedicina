package maison.imedicina.file.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashSet;
import maison.imedicina.file.PessoaFile;
import maison.imedicina.model.Pessoa;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maison Chaves
 */
@Component
public class PessoaFileImpl implements PessoaFile {

    @Override
    public Collection<Pessoa> read(InputStream myFile) throws FileNotFoundException, IOException {
        XSSFWorkbook myWorkBook = new XSSFWorkbook(myFile);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        Collection<Pessoa> pessoas = new HashSet<>();

        for (Row row : mySheet) {
            if (hasName(row)) {
                pessoas.add(toPessoa(row));
            }
        }
        return pessoas;
    }

    /**
     *
     * @param row
     * @return
     */
    private Pessoa toPessoa(Row row) {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(row.getCell(0).getStringCellValue());

        if (row.getCell(1) != null) {
            pessoa.setTelefone(new DecimalFormat("#").format(row.getCell(1).getNumericCellValue()));
        }
        if (row.getCell(2) != null) {
            pessoa.setEmail(row.getCell(2).getStringCellValue());
        }
        if (row.getCell(3) != null) {
            pessoa.setDataNascimento(row.getCell(3).getDateCellValue());
        }

        return pessoa;
    }

    /**
     *
     * @param row
     * @return
     */
    private boolean hasName(Row row) {
        return row.getCell(0) != null;
    }
}
