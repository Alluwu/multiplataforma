package com.mp.proveedor.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "unidadMedida")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UnidadMedida extends BaseEntity {
    @Id
    @Column(name = "idUnidad", length = 50)
    private String idUnidad;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
}
