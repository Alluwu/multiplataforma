package com.mp.proveedor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mp.proveedor.domain.entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, UUID> {
    boolean existsByNitIgnoreCase(String nit);

    boolean existsByNitIgnoreCaseAndIdProveedorNot(String nit, UUID idProveedor);

}