package com.vnoxiaene.fazendinha.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true, setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
public class GalinhaResponseDTO {

  private UUID uuid;
  private String nome;
  private String dataNascimento;
}
