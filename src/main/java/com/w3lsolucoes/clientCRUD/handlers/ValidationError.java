package com.w3lsolucoes.clientCRUD.handlers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

    public List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError(String field, String defaultMessage) {
        field = field.substring(0, 1).toUpperCase() + field.substring(1);;
        errors.add(new FieldMessage(field, defaultMessage));
    }

}