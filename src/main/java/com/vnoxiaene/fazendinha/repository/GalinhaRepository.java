package com.vnoxiaene.fazendinha.repository;

import com.vnoxiaene.fazendinha.entity.Galinha;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalinhaRepository extends JpaRepository<Galinha, Long> {

  Galinha findByUuid(UUID uuid);
}
