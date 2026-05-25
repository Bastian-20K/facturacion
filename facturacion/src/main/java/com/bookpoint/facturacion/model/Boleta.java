package com.bookpoint.facturacion.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boletas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBoleta;

    private Long ventaId;

    private Long usuarioId;

    private String rutEmisor;

    private Integer subtotal;

    private Integer descuento;

    private Integer total;

    @ElementCollection
    @CollectionTable(
            name = "detalle_boleta",
            joinColumns = @JoinColumn(name = "boleta_id")
    )
    private List<DetalleBoleta> detalleProductos;

    private LocalDateTime fechaBoleta;
}
