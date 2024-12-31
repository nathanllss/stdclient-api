package com.study.nclient_api.dto.errors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> erros = new ArrayList<>();
    public ValidationError(Instant moment, Integer status, String error, String path) {
        super(moment, status, error, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String message) {
        erros.add(new FieldMessage(fieldName, message));
    }
}
