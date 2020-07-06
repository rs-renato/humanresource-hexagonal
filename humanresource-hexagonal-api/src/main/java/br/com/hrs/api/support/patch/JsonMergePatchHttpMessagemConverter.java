package br.com.hrs.api.support.patch;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonMergePatch;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.IOException;

@Component
public class JsonMergePatchHttpMessagemConverter extends AbstractHttpMessageConverter<JsonMergePatch> {

    public JsonMergePatchHttpMessagemConverter() {
        super(MediaType.valueOf("application/merge-patch+json"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return JsonMergePatch.class.isAssignableFrom(clazz);
    }

    @Override
    protected JsonMergePatch readInternal(Class<? extends JsonMergePatch> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        try (JsonReader reader = Json.createReader(inputMessage.getBody())){
            return Json.createMergePatch(reader.readValue());
        }catch (Exception ex){
            throw new HttpMessageNotReadableException(ex.getMessage(), inputMessage);
        }
    }

    @Override
    protected void writeInternal(JsonMergePatch JsonMergePatch, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        try(JsonWriter writer = Json.createWriter(outputMessage.getBody())){
            writer.write(JsonMergePatch.toJsonValue());
        }catch (Exception ex){
            throw new HttpMessageNotWritableException(ex.getMessage());
        }
    }
}
