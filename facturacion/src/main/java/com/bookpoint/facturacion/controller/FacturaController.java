package com.bookpoint.facturacion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookpoint.facturacion.dto.FacturaDTO;
import com.bookpoint.facturacion.model.Factura;
import com.bookpoint.facturacion.service.FacturaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/facturas")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaService service;

    @PostMapping
    public Factura crear(@RequestBody FacturaDTO dto) {
        return service.crearFactura(dto);
    }

    @GetMapping
    public List<Factura> listarTodas() {
        return service.listarFacturas();
    }

    @GetMapping("/{id}")
    public Factura buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Factura> porUsuario(@PathVariable Long usuarioId) {
        return service.buscarPorUsuario(usuarioId);
    }
}
