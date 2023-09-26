package com.vnoxiaene.fazendinha.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GalinhaResponseDTO {

    private UUID uuid;
    private String nome;
    private String dataNascimento;
}
