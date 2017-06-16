package maison.imedicina.serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CustomDateSerializerTest {
    @Test
    public void serialize() throws IOException {
        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();

        new CustomDateSerializer().serialize(new Date(1497625222766L), jsonGenerator, serializerProvider);
        jsonGenerator.flush();
        assertThat(jsonWriter.toString(), is(equalTo("\"16/06/2017\"")));
    }

}