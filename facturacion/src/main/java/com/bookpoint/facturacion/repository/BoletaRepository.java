package com.bookpoint.facturacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookpoint.facturacion.model.Boleta;

public interface BoletaRepository extends JpaRepository<Boleta, Long>{
    List<Boleta> findByUsuarioId(Long usuarioId);

    List<Boleta> findByVentaId(Long ventaId);
}
