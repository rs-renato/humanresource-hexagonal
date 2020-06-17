package br.com.hrs.api.support;

import br.com.hrs.api.exception.PatchException;
import br.com.hrs.api.validation.FieldValidationStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
public class PatchSupport {

    private ObjectMapper objectMapper;
    private Validator validator;

    @Autowired
    public PatchSupport(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    public <T> T apply(JsonPatch patch, T type){
        try {
            JsonNode patched = patch.apply(objectMapper.convertValue(type, JsonNode.class));

            Set<ConstraintViolation<T>> violations = validator.validate(type, FieldValidationStrategy.Patch.class);

            if (!violations.isEmpty()){
                throw new ConstraintViolationException(violations);
            }

            return objectMapper.treeToValue(patched, (Class<T>) type.getClass());
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new PatchException(e);
        }
    }
}
