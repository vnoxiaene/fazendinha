package com.vnoxiaene.fazendinha.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Entity
@Table(name = "galinhas")
@Data
public class Galinha {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;
  private String nome;
  private LocalDate dataNascimento;

}
