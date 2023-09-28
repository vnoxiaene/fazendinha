package com.vnoxiaene.fazendinha.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true, setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class GalinhaRequestDTO {

    @NotNull(message = "nome nao pode ser nulo")
    private String nome;
    private String dataNascimento;
}
