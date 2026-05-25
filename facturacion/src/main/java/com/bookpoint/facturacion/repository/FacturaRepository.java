package com.bookpoint.facturacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookpoint.facturacion.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long>{
    List<Factura> findByUsuarioId(Long usuarioId);

    List<Factura> findByVentaId(Long ventaId);
}
