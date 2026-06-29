package com.bookpoint.facturacion.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookpoint.facturacion.dto.BoletaDTO;
import com.bookpoint.facturacion.model.Boleta;
import com.bookpoint.facturacion.model.DetalleBoleta;
import com.bookpoint.facturacion.model.TipoProducto;
import com.bookpoint.facturacion.repository.BoletaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoletaService {
    private final BoletaRepository repository;
    private final RestTemplate restTemplate;

    public Boleta crearBoleta(BoletaDTO request) {

        Map<String, Object> venta = restTemplate.getForObject("http://localhost:8085/api/v1/ventas/" + request.getVentaId(),Map.class);

        List<Map<String, Object>> detallesMap =(List<Map<String, Object>>) venta.get("detalleProductos");

        List<DetalleBoleta> detalles =
                detallesMap.stream()
                        .map(p -> DetalleBoleta.builder()
                                .productoId(Long.valueOf(p.get("productoId").toString()))
                                .tipoProducto(TipoProducto.valueOf(p.get("tipoProducto").toString()))
                                .nombreProducto(p.get("nombreProducto").toString())
                                .precio(Integer.valueOf(p.get("precio").toString()))
                                .cantidad(Integer.valueOf(p.get("cantidad").toString()))
                                .sku(p.get("sku").toString())
                                .sucursalId(Long.valueOf(p.get("sucursalId").toString()))
                                .build()
                        ).toList();

        Boleta boleta = Boleta.builder()
                .ventaId(Long.valueOf(venta.get("idVenta").toString()))
                .usuarioId(Long.valueOf(venta.get("usuarioId").toString()))
                .rutEmisor(request.getRutEmisor())
                .subtotal(Integer.valueOf(venta.get("subtotal").toString()))
                .descuento(Integer.valueOf(venta.get("descuentoCupon").toString()))
                .total(Integer.valueOf(venta.get("totalPagar").toString()))
                .detalleProductos(detalles)
                .fechaBoleta(LocalDateTime.now())
                .build();
        return repository.save(boleta);
    }

    public Boleta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada"));
    }

    public List<Boleta> listarBoletas() {
        return repository.findAll();
    }

    public List<Boleta> buscarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}
