package com.bookpoint.facturacion.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(
    properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
    }
)
@AutoConfigureMockMvc
public class FacturaControllerIT {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RestTemplate restTemplate;

    @Test
    void listarFacturas_deberiaRetornar200()
            throws Exception {

        mockMvc.perform(
                get("/api/v1/facturas")
        )
        .andExpect(
                status().isOk()
        );
    }

    @Test
    void buscarFacturasPorUsuario_deberiaRetornar200()
            throws Exception {

        mockMvc.perform(
                get("/api/v1/facturas/usuario/1")
        )
        .andExpect(
                status().isOk()
        );
    }
}

