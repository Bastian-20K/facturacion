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
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    private Long ventaId;

    private Long usuarioId;

    private String rutComprador;

    private Integer subtotal;

    private Integer descuento;

    private Integer total;

    @ElementCollection
    @CollectionTable(
            name = "detalle_factura",
            joinColumns = @JoinColumn(name = "factura_id")
    )
    private List<DetalleFactura> detalleProductos;

    private LocalDateTime fechaEmision;

    private LocalDateTime fechaVencimiento;
}
