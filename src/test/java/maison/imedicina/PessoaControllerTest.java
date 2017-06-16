package maison.imedicina;

import com.fasterxml.jackson.databind.ObjectMapper;
import maison.imedicina.model.Pessoa;
import maison.imedicina.service.PessoaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Test do Controller de pessoa.
 *
 * @author Maison Chaves
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = PessoaController.class, secure = false)
public class PessoaControllerTest {

    private final Pessoa pessoaMock = new Pessoa("Maison", "3133334444", "maison@gmail.com", new Date());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getPerson() throws Exception {

        Mockito.when(pessoaService.getPessoaById(Mockito.anyLong())).thenReturn(pessoaMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pessoa/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":null,\"nome\":\"Maison\",\"telefone\":\"3133334444\",\"email\":\"maison@gmail.com\",\"dataNascimento\":\"16/06/2017\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    @Test
    public void listPerson() throws Exception {

        Collection<Pessoa> pessoaListMock = new HashSet<Pessoa>();
        pessoaListMock.add(pessoaMock);

        Mockito.when(pessoaService.list()).thenReturn(pessoaListMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pessoa").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\":null,\"nome\":\"Maison\",\"telefone\":\"3133334444\",\"email\":\"maison@gmail.com\",\"dataNascimento\":\"16/06/2017\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    @Test
    public void createPerson() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post("/pessoa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new Pessoa()))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }
}
