package com.mp.proveedor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Proveedor extends BaseEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID idProveedor;
    @Column(nullable = false, length = 150)
    private String nit;
    @Column( nullable = false, length = 250)
    private String nombre;
    @Column( length = 250)
    private String direccion;
    @Column( length = 50)
    private String telefono;
    @Column(length = 150)
    private String correoElectronico;
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenCompra> ordenes = new ArrayList();

}
