package br.com.hrs.api.support.patch;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonPatch;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.IOException;

@Component
public class JsonPatchHttpMessagemConverter extends AbstractHttpMessageConverter<JsonPatch> {

    public JsonPatchHttpMessagemConverter() {
        super(MediaType.valueOf("application/json-patch+json"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return JsonPatch.class.isAssignableFrom(clazz);
    }

    @Override
    protected JsonPatch readInternal(Class<? extends JsonPatch> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        try (JsonReader reader = Json.createReader(inputMessage.getBody())){
            return Json.createPatch(reader.readArray());
        }catch (Exception ex){
            throw new HttpMessageNotReadableException(ex.getMessage(), inputMessage);
        }
    }

    @Override
    protected void writeInternal(JsonPatch jsonPatch, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        try(JsonWriter writer = Json.createWriter(outputMessage.getBody())){
            writer.write(jsonPatch.toJsonArray());
        }catch (Exception ex){
            throw new HttpMessageNotWritableException(ex.getMessage());
        }
    }
}
