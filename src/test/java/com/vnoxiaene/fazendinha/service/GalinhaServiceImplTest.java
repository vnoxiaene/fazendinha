package com.vnoxiaene.fazendinha.service;

import com.vnoxiaene.fazendinha.dto.GalinhaRequestDTO;
import com.vnoxiaene.fazendinha.dto.GalinhaResponseDTO;
import com.vnoxiaene.fazendinha.entity.Galinha;
import com.vnoxiaene.fazendinha.repository.GalinhaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class GalinhaServiceImplTest {

    @Mock
    GalinhaRepository galinhaRepository;

    GalinhaServiceImpl galinhaService;

    @Test
    void quandoCadastrarRetornarGalinhaResponseDTO_OK(){
        galinhaService = new GalinhaServiceImpl(galinhaRepository);
        //given - dado
        GalinhaRequestDTO pititica = GalinhaRequestDTO.builder()
                .setDataNascimento("2023-09-10")
                .setNome("pititica")
                .build();
        Galinha galinha = new Galinha();
        UUID uuid = UUID.fromString("4a28b57c-d564-4077-a8bc-1a8d0648ec2d");
        galinha.setNome(pititica.getNome());
        galinha.setUuid(uuid);
        galinha.setDataNascimento(LocalDate.parse(pititica.getDataNascimento()));

        Galinha galinhadb = new Galinha();
        galinhadb.setUuid(galinha.getUuid());
        galinhadb.setId(1l);
        galinhadb.setDataNascimento(galinha.getDataNascimento());
        galinhadb.setNome(galinha.getNome());
        Mockito.when(galinhaRepository.save(galinha)).thenReturn(galinhadb);
        try (MockedStatic<UUID> uuidMockedStatic = Mockito.mockStatic(UUID.class, InvocationOnMock::callRealMethod)) {
            uuidMockedStatic.when(UUID::randomUUID).thenReturn(uuid);
            //when - quando
            GalinhaResponseDTO galinhaResponseDTO = galinhaService.cadastrar(pititica);
            //then - então
            Assertions.assertThat(galinhaResponseDTO.getDataNascimento()).isEqualTo("2023-09-10");
            Assertions.assertThat(galinhaResponseDTO.getNome()).isEqualTo("pititica");
            Assertions.assertThat(galinhaResponseDTO.getUuid()).isEqualTo(UUID.fromString("4a28b57c-d564-4077-a8bc-1a8d0648ec2c"));
        }
    }
}