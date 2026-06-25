package com.bookpoint.facturacion.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookpoint.facturacion.model.Factura;
import com.bookpoint.facturacion.service.FacturaService;

@WebMvcTest(FacturaController.class)
public class FacturaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacturaService service;

    @Test
    void crearFactura_deberiaRetornar200()
            throws Exception {

        Factura factura =
                Factura.builder()
                        .idFactura(1L)
                        .build();

        when(service.crearFactura(org.mockito.ArgumentMatchers.any()))
                .thenReturn(factura);

        mockMvc.perform(
                post("/api/v1/facturas")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "ventaId":1
                                }
                                """)
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void listarFacturas_deberiaRetornar200()
            throws Exception {

        when(service.listarFacturas())
                .thenReturn(List.of());

        mockMvc.perform(
                get("/api/v1/facturas")
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void buscarFacturaPorId_deberiaRetornar200()
            throws Exception {

        Factura factura =
                Factura.builder()
                        .idFactura(1L)
                        .build();

        when(service.buscarPorId(1L))
                .thenReturn(factura);

        mockMvc.perform(
                get("/api/v1/facturas/1")
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void buscarFacturasPorUsuario_deberiaRetornar200()
            throws Exception {

        when(service.buscarPorUsuario(10L))
                .thenReturn(List.of());

        mockMvc.perform(
                get("/api/v1/facturas/usuario/10")
        )
        .andExpect(
                status().isOk()
        );
    }
}

