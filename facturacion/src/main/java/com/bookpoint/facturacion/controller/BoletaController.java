package com.bookpoint.facturacion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookpoint.facturacion.dto.BoletaDTO;
import com.bookpoint.facturacion.model.Boleta;
import com.bookpoint.facturacion.service.BoletaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/boletas")
@RequiredArgsConstructor
public class BoletaController {
    private final BoletaService service;

    @PostMapping
    public Boleta crear(@RequestBody BoletaDTO dto) {
        return service.crearBoleta(dto);
    }

    @GetMapping
    public List<Boleta> listarTodas() {
        return service.listarBoletas();
    }

    @GetMapping("/{id}")
    public Boleta buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Boleta> porUsuario(@PathVariable Long usuarioId) {
        return service.buscarPorUsuario(usuarioId);
    }
}
