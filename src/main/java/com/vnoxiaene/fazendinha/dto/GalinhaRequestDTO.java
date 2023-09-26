package com.vnoxiaene.fazendinha.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true, setterPrefix = "set")
public class GalinhaRequestDTO {

    private String nome;
    private String dataNascimento;
}
