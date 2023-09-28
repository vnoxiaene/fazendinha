package com.vnoxiaene.fazendinha.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnoxiaene.fazendinha.dto.GalinhaRequestDTO;
import com.vnoxiaene.fazendinha.dto.GalinhaResponseDTO;
import com.vnoxiaene.fazendinha.exception.InvalidDataNascimentoException;
import com.vnoxiaene.fazendinha.service.GalinhaService;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class GalinhaControllerTest {

  @Mock
  GalinhaService galinhaService;
  @InjectMocks
  GalinhaController galinhaController;

  MockedStatic<UUID> uuidMockedStatic;
  UUID uuid = UUID.fromString("4a28b57c-d564-4077-a8bc-1a8d0648ec2c");

  @BeforeEach
  void setUp() {
    uuidMockedStatic = Mockito.mockStatic(UUID.class, InvocationOnMock::callRealMethod);
    uuidMockedStatic.when(UUID::randomUUID).thenReturn(uuid);
  }

  @AfterEach
  void tearDown() {
    uuidMockedStatic.close();
  }

  @Test
  @DisplayName("Retornar galinha quando request valida")
  void retornaGalinhaQuandoRequestValida() throws Exception {
    //given
    var jsonResponse = "{\"uuid\":\"4a28b57c-d564-4077-a8bc-1a8d0648ec2c\",\"nome\":\"pititica\",\"dataNascimento\":\"2023-08-01\"}";
    var pititica = GalinhaRequestDTO.builder()
        .setNome("pititica")
        .setDataNascimento("2023-08-01")
        .build();
    var responseDTO = GalinhaResponseDTO.builder()
        .setUuid(uuid)
        .setNome("pititica")
        .setDataNascimento("2023-08-01")
        .build();
    BDDMockito.given(galinhaService.cadastrar(pititica)).willReturn(responseDTO);

    // ----------------------------------------------------------------------

    String conteudo = (new ObjectMapper()).writeValueAsString(pititica);
    //when
    MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
        MockMvcRequestBuilders.post("/v1/galinha")
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudo);
    //then
    MockMvcBuilders.standaloneSetup(galinhaController)
        .build()
        .perform(mockHttpServletRequestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string(jsonResponse));

  }

  @Test
  @DisplayName("Retornar bad request quando request esta sem nome")
  void retornaBadRequestQuandoRequestSemNome() throws Exception {
    //given
    var pititica = GalinhaRequestDTO.builder()
        .setDataNascimento("2023-08-01")
        .build();

    // ----------------------------------------------------------------------

    String conteudo = (new ObjectMapper()).writeValueAsString(pititica);
    //when
    MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
        MockMvcRequestBuilders.post("/v1/galinha")
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudo);
    //then
    MockMvcBuilders.standaloneSetup(galinhaController)
        .build()
        .perform(mockHttpServletRequestBuilder)
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Disabled
  @Test
  @DisplayName("Retornar bad request quando request data esta maior que hoje")
  void retornaBadRequestQuandoRequestTemDataMaiorQueHoje() throws Exception {
    //given
    var pititica = GalinhaRequestDTO.builder()
        .setNome("pititica")
        .setDataNascimento(LocalDate.now().plusDays(1L).toString())
        .build();
    Mockito.doThrow(InvalidDataNascimentoException.class).when(galinhaService).cadastrar(pititica);
    // ----------------------------------------------------------------------

    String conteudo = (new ObjectMapper()).writeValueAsString(pititica);
    //when
    MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
        MockMvcRequestBuilders.post("/v1/galinha")
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudo);
    //then
    MockMvcBuilders.standaloneSetup(galinhaController)
        .build()
        .perform(mockHttpServletRequestBuilder)
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

}