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

import com.bookpoint.facturacion.model.Boleta;
import com.bookpoint.facturacion.service.BoletaService;

@WebMvcTest(BoletaController.class)
public class BoletaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BoletaService service;

    @Test
    void crearBoleta_deberiaRetornar200()
            throws Exception {

        Boleta boleta =
                Boleta.builder()
                        .idBoleta(1L)
                        .build();

        when(service.crearBoleta(org.mockito.ArgumentMatchers.any()))
                .thenReturn(boleta);

        mockMvc.perform(
                post("/api/v1/boletas")
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
    void listarBoletas_deberiaRetornar200()
            throws Exception {

        when(service.listarBoletas())
                .thenReturn(List.of());

        mockMvc.perform(
                get("/api/v1/boletas")
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void buscarBoletaPorId_deberiaRetornar200()
            throws Exception {

        Boleta boleta =
                Boleta.builder()
                        .idBoleta(1L)
                        .build();

        when(service.buscarPorId(1L))
                .thenReturn(boleta);

        mockMvc.perform(
                get("/api/v1/boletas/1")
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void buscarBoletasPorUsuario_deberiaRetornar200()
            throws Exception {

        when(service.buscarPorUsuario(10L))
                .thenReturn(List.of());

        mockMvc.perform(
                get("/api/v1/boletas/usuario/10")
        )
        .andExpect(
                status().isOk()
        );
    }
}

