package com.bookpoint.facturacion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.bookpoint.facturacion.dto.FacturaDTO;
import com.bookpoint.facturacion.model.Factura;
import com.bookpoint.facturacion.repository.FacturaRepository;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTest {
    
    @Mock
    private FacturaRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FacturaService service;

    private Factura factura;

    @BeforeEach
    void setup() {

        factura = Factura.builder()
                .idFactura(1L)
                .usuarioId(10L)
                .ventaId(100L)
                .subtotal(10000)
                .total(9000)
                .build();
    }

    @Test
    void crearFactura_deberiaGuardarFactura() {

        FacturaDTO dto = new FacturaDTO();
        dto.setVentaId(100L);

        Map<String,Object> detalle =
                Map.of(
                        "productoId",1L,
                        "tipoProducto","LIBRO",
                        "nombreProducto","Libro Test",
                        "precio",10000,
                        "cantidad",1,
                        "sku","SKU001",
                        "sucursalId",1L
                );

        Map<String,Object> venta =
                Map.of(
                        "idVenta",100L,
                        "usuarioId",10L,
                        "rutComprador","11111111-1",
                        "subtotal",10000,
                        "descuentoCupon",1000,
                        "totalPagar",9000,
                        "detalleProductos", List.of(detalle)
                );

        when(restTemplate.getForObject(
                any(String.class),
                any(Class.class)))
                .thenReturn(venta);

        when(repository.save(any(Factura.class)))
                .thenReturn(factura);

        Factura resultado =
                service.crearFactura(dto);

        assertNotNull(resultado);
        assertEquals(100L, resultado.getVentaId());
    }

    @Test
    void buscarPorId_deberiaRetornarFactura() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(factura));

        Factura resultado =
                service.buscarPorId(1L);

        assertEquals(
                factura.getIdFactura(),
                resultado.getIdFactura()
        );
    }

    @Test
    void buscarPorId_deberiaLanzarExcepcion() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> service.buscarPorId(99L)
        );
    }

    @Test
    void listarFacturas_deberiaRetornarLista() {

        when(repository.findAll())
                .thenReturn(List.of(factura));

        List<Factura> resultado =
                service.listarFacturas();

        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorUsuario_deberiaRetornarFacturas() {

        when(repository.findByUsuarioId(10L))
                .thenReturn(List.of(factura));

        List<Factura> resultado =
                service.buscarPorUsuario(10L);

        assertEquals(1, resultado.size());
    }
}

