package com.bookpoint.facturacion.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.bookpoint.facturacion.dto.BoletaDTO;
import com.bookpoint.facturacion.model.Boleta;
import com.bookpoint.facturacion.repository.BoletaRepository;

@ExtendWith(MockitoExtension.class)
public class BoletaServiceTest {
    
    @Mock
    private BoletaRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BoletaService service;

    private Boleta boleta;

    @BeforeEach
    void setup() {

        boleta = Boleta.builder()
                .idBoleta(1L)
                .usuarioId(10L)
                .ventaId(100L)
                .subtotal(10000)
                .total(9000)
                .build();
    }

    @Test
    void crearBoleta_deberiaGuardarBoleta() {

        BoletaDTO dto = new BoletaDTO();
        dto.setVentaId(100L);

        Map<String, Object> detalle =
                Map.of(
                        "productoId", 1L,
                        "tipoProducto", "LIBRO",
                        "nombreProducto", "Libro Test",
                        "precio", 10000,
                        "cantidad", 1,
                        "sku", "SKU001",
                        "sucursalId", 1L
                );

        Map<String, Object> venta =
                Map.of(
                        "idVenta", 100L,
                        "usuarioId", 10L,
                        "rutComprador", "11111111-1",
                        "subtotal", 10000,
                        "descuentoCupon", 1000,
                        "totalPagar", 9000,
                        "detalleProductos", List.of(detalle)
                );

        when(
                restTemplate.getForObject(
                        any(String.class),
                        any(Class.class)
                )
        ).thenReturn(venta);

        when(
                repository.save(any(Boleta.class))
        ).thenReturn(boleta);

        Boleta resultado =
                service.crearBoleta(dto);

        assertNotNull(resultado);

        assertEquals(
                100L,
                resultado.getVentaId()
        );
    }

    @Test
    void buscarPorId_deberiaRetornarBoleta() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(boleta));

        Boleta resultado =
                service.buscarPorId(1L);

        assertEquals(
                boleta.getIdBoleta(),
                resultado.getIdBoleta()
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
    void listarBoletas_deberiaRetornarLista() {

        when(repository.findAll())
                .thenReturn(List.of(boleta));

        List<Boleta> resultado =
                service.listarBoletas();

        assertEquals(
                1,
                resultado.size()
        );
    }

    @Test
    void buscarPorUsuario_deberiaRetornarBoletas() {

        when(repository.findByUsuarioId(10L))
                .thenReturn(List.of(boleta));

        List<Boleta> resultado =
                service.buscarPorUsuario(10L);

        assertEquals(
                1,
                resultado.size()
        );
    }

    @Test
        void buscarPorUsuario_deberiaRetornarListaVacia() {

        when(repository.findByUsuarioId(999L))
                .thenReturn(List.of());

        List<Boleta> resultado =
                service.buscarPorUsuario(999L);

        assertNotNull(resultado);

        assertEquals(
                0,
                resultado.size()
        );
    }

    @Test
        void listarBoletas_deberiaRetornarListaVacia() {

        when(repository.findAll())
                .thenReturn(List.of());

        List<Boleta> resultado =
                service.listarBoletas();

        assertNotNull(resultado);

        assertEquals(
                0,
                resultado.size()
        );
}
}

