package com.mp.proveedor.repository;


import com.mp.proveedor.domain.entities.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, UUID> {
    boolean existsByDescripcionIgnoreCase(String descripcion);
    boolean existsByDescripcionIgnoreCaseAndIdCategoriaNot(String descripcion, UUID idCategoria);
}
