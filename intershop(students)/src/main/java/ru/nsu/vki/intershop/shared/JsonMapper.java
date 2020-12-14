package ru.nsu.vki.intershop.shared;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JsonMapper {
    private static final ObjectMapper m = new ObjectMapper();
    private static final JsonFactory jf = new JsonFactory();

    public static <T> T fromJson(String jsonAsString, Class<T> pojoClass) {
        try {
            return m.readValue(jsonAsString, pojoClass);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toJson(Object pojo, boolean prettyPrint) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator jg = jf.createGenerator(sw);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        m.writeValue(jg, pojo);
        return sw.toString();
    }
}
