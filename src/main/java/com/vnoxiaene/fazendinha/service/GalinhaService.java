package com.vnoxiaene.fazendinha.service;

import com.vnoxiaene.fazendinha.dto.GalinhaRequestDTO;
import com.vnoxiaene.fazendinha.dto.GalinhaResponseDTO;

import java.util.List;
import java.util.UUID;

public interface GalinhaService {
    GalinhaResponseDTO cadastrar(GalinhaRequestDTO galinhaRequestDTO);

    GalinhaResponseDTO get(UUID uuid);

    void deletar(UUID uuid);

    GalinhaResponseDTO alterar(UUID uuid, GalinhaRequestDTO galinhaRequestDTO);

    List<GalinhaResponseDTO> listarGalinhas();
}
