package com.vnoxiaene.fazendinha.service;

import com.vnoxiaene.fazendinha.dto.GalinhaRequestDTO;
import com.vnoxiaene.fazendinha.dto.GalinhaResponseDTO;
import com.vnoxiaene.fazendinha.entity.Galinha;
import com.vnoxiaene.fazendinha.repository.GalinhaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalinhaServiceImpl implements GalinhaService {

    private final GalinhaRepository galinhaRepository;
    @Override
    public GalinhaResponseDTO cadastrar(GalinhaRequestDTO galinhaRequestDTO) {
        log.info("Cadastrando galinha: {}", galinhaRequestDTO);
        Galinha galinha = new Galinha();
        galinha.setUuid(UUID.randomUUID());
        galinha.setNome(galinhaRequestDTO.getNome());
        galinha.setDataNascimento(parseStringToLocalDate(galinhaRequestDTO));
        Galinha galinhaDB = galinhaRepository.save(galinha);
        return getGalinhaResponseDTO(galinhaDB);
    }

    private static GalinhaResponseDTO getGalinhaResponseDTO(Galinha galinhaDB) {
        GalinhaResponseDTO galinhaResponseDTO = new GalinhaResponseDTO();
        galinhaResponseDTO.setUuid(galinhaDB.getUuid());
        galinhaResponseDTO.setNome(galinhaDB.getNome());
        galinhaResponseDTO.setDataNascimento(galinhaDB.getDataNascimento().toString());
        return galinhaResponseDTO;
    }

    @Override
    public GalinhaResponseDTO get(UUID uuid) {
        return getGalinhaResponseDTO( galinhaRepository.findByUuid(uuid));
    }

    @Override
    public void deletar(UUID uuid) {
        Galinha galinha = galinhaRepository.findByUuid(uuid);
        galinhaRepository.delete(galinha);
    }

    @Override
    public GalinhaResponseDTO alterar(UUID uuid, GalinhaRequestDTO galinhaRequestDTO) {
        Galinha galinha = galinhaRepository.findByUuid(uuid);
        galinha.setNome(galinha.getNome());
        galinha.setDataNascimento(parseStringToLocalDate(galinhaRequestDTO));
        Galinha savedGalinha = galinhaRepository.save(galinha);
        return getGalinhaResponseDTO(savedGalinha);
    }

    @Override
    public List<GalinhaResponseDTO> listarGalinhas() {
        List<Galinha> galinhas = galinhaRepository.findAll();
        return galinhas.stream().map(GalinhaServiceImpl::getGalinhaResponseDTO).collect(Collectors.toList());
    }

    private static LocalDate parseStringToLocalDate(GalinhaRequestDTO galinhaRequestDTO) {
        return LocalDate.parse(galinhaRequestDTO.getDataNascimento());
    }
}
