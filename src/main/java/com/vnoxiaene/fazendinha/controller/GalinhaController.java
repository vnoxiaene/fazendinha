package com.vnoxiaene.fazendinha.controller;

import com.vnoxiaene.fazendinha.dto.GalinhaRequestDTO;
import com.vnoxiaene.fazendinha.dto.GalinhaResponseDTO;
import com.vnoxiaene.fazendinha.service.GalinhaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/galinha")
@RequiredArgsConstructor
@Slf4j
public class GalinhaController {
    private final GalinhaService galinhaService;
    @PostMapping
    public ResponseEntity<GalinhaResponseDTO> cadastrar(@RequestBody GalinhaRequestDTO galinhaRequestDTO){
            return ResponseEntity.ok(galinhaService.cadastrar(galinhaRequestDTO));
    }
    @GetMapping("/{uuid}")
    public  ResponseEntity<GalinhaResponseDTO> get(@PathVariable UUID uuid){
        return ResponseEntity.ok(galinhaService.get(uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deletar(@PathVariable UUID uuid){
        galinhaService.deletar(uuid);
        return ResponseEntity.accepted().body("Galinha deletada com sucesso!");
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<GalinhaResponseDTO> alterar(@PathVariable UUID uuid, @RequestBody GalinhaRequestDTO galinhaRequestDTO){
        return ResponseEntity.ok(galinhaService.alterar(uuid, galinhaRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<GalinhaResponseDTO>> listarGalinhas(){
        return ResponseEntity.ok(galinhaService.listarGalinhas());
    }

}
