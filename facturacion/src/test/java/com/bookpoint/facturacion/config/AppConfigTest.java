package com.bookpoint.facturacion.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class AppConfigTest {
    
    @Test
    void restTemplateBean_deberiaCrearse() {

        AppConfig config = new AppConfig();

        RestTemplate restTemplate = config.restTemplate();

        assertNotNull(restTemplate);
    }
}

