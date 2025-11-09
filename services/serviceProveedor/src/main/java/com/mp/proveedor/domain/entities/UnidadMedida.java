package com.mp.proveedor.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UnidadMedida extends BaseEntity {
    @Id
    @Column(length = 50)
    private String idUnidad;
    @Column( nullable = false, length = 100)
    private String nombre;
}
