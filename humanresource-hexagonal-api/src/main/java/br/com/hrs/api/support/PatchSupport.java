package br.com.hrs.api.support;

import br.com.hrs.api.exception.PatchException;
import br.com.hrs.api.validation.FieldValidationStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.json.JsonMergePatch;
import javax.json.JsonValue;
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

    public <T> T apply(JsonMergePatch mergePatch, T type){

        try {

            JsonValue requestTarget = objectMapper.convertValue(type.getClass().newInstance(), JsonValue.class);

            JsonValue requestPatched = mergePatch.apply(requestTarget);

            T requestBean = objectMapper.convertValue(requestPatched, (Class<T>) type.getClass());

            Set<ConstraintViolation<T>> violations = validator.validate(requestBean, FieldValidationStrategy.Patch.class);

            if (!violations.isEmpty()){
                throw new ConstraintViolationException(violations);
            }

            JsonValue target = objectMapper.convertValue(type, JsonValue.class);

            JsonValue patched = mergePatch.apply(target);

            T bean = objectMapper.convertValue(patched, (Class<T>) type.getClass());

            return bean;
        } catch (InstantiationException | IllegalAccessException e){
            throw new PatchException(e);
        }
    }
}
