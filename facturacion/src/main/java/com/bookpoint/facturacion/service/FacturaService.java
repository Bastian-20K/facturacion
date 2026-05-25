package com.bookpoint.facturacion.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookpoint.facturacion.dto.FacturaDTO;
import com.bookpoint.facturacion.model.DetalleFactura;
import com.bookpoint.facturacion.model.Factura;
import com.bookpoint.facturacion.model.TipoProducto;
import com.bookpoint.facturacion.repository.FacturaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacturaService {
    private final FacturaRepository repository;
    private final RestTemplate restTemplate;

    public Factura crearFactura(FacturaDTO request) {
        Map<String, Object> venta = restTemplate.getForObject("http://localhost:8085/api/v1/ventas/" + request.getVentaId(),Map.class);

        List<Map<String, Object>> detallesMap =(List<Map<String, Object>>) venta.get("detalleProductos");

        List<DetalleFactura> detalles =
                detallesMap.stream()
                        .map(p -> DetalleFactura.builder()
                                .productoId(Long.valueOf(p.get("productoId").toString()))
                                .tipoProducto(TipoProducto.valueOf(p.get("tipoProducto").toString()))
                                .nombreProducto(p.get("nombreProducto").toString())
                                .precio(Integer.valueOf(p.get("precio").toString()))
                                .sucursalId(Long.valueOf(p.get("sucursalId").toString()))
                                .build()
                        ).toList();

        Factura factura = Factura.builder()
                .ventaId(Long.valueOf(venta.get("idVenta").toString()))
                .usuarioId(Long.valueOf(venta.get("usuarioId").toString()))
                .rutComprador(venta.get("rutComprador").toString())
                .subtotal(Integer.valueOf(venta.get("subtotal").toString()))
                .descuento(Integer.valueOf(venta.get("descuentoCupon").toString()))
                .total(Integer.valueOf(venta.get("totalPagar").toString()))
                .detalleProductos(detalles)
                .fechaEmision(LocalDateTime.now())
                .fechaVencimiento(LocalDateTime.now().plusDays(30))
                .build();
        return repository.save(factura);
    }

    public Factura buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    public List<Factura> listarFacturas() {
        return repository.findAll();
    }

    public List<Factura> buscarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}
