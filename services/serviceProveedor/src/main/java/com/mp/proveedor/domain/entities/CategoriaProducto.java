package com.mp.proveedor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity @Table(name = "categoriaProducto")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoriaProducto extends BaseEntity  {
    @Id
    @Column(name = "idCategoria", columnDefinition = "uuid")
    private UUID idCategoria;
    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;

}
