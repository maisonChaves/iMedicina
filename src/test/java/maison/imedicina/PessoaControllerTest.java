package maison.imedicina;

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

import java.util.Date;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PessoaController.class, secure = false)
public class PessoaControllerTest {

    Pessoa pessoaMock = new Pessoa("Maison", "3133334444", "maison@gmail.com", new Date());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @Test
    public void retrieveDetailsForCourse() throws Exception {

        Mockito.when(pessoaService.getPessoaById(Mockito.anyLong())).thenReturn(pessoaMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pessoa/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"id\":null,\"nome\":\"Maison\",\"telefone\":\"3133334444\",\"email\":\"maison@gmail.com\",\"dataNascimento\":\"16/06/2017\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
