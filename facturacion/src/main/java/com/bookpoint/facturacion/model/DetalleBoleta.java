package com.bookpoint.facturacion.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleBoleta {
    private Long productoId;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    private String nombreProducto;

    private Integer precio;

    private Long sucursalId;
}
