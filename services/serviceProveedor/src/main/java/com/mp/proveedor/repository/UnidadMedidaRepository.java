package com.mp.proveedor.repository;

import com.mp.proveedor.domain.entities.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, String> {
    boolean existsByNombreIgnoreCase(String nombre);    
} 
