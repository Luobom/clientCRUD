package com.w3lsolucoes.clientCRUD.dto;

import com.w3lsolucoes.clientCRUD.entities.Client;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientDTO(
        Long id,
        @NotBlank(message = "Campo obrigatório")
        String name,
        @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
        String cpf,
        @Positive(message = "O valor deve ser positivo")
        Double income,
        @PastOrPresent(message = "A data de nascimento não pode ser futura")
        LocalDate birthDate,
        @Min(value = 0, message = "O valor deve ser positivo")
        Integer children
) {
    public ClientDTO(Client entity) {
        this(entity.getId(), entity.getName(), entity.getCpf(), entity.getIncome(), entity.getBirthDate(), entity.getChildren());
    }
}
