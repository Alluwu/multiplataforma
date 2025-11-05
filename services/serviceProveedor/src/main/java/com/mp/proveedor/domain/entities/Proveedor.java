package com.mp.proveedor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity @Table(name = "proveedores")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Proveedor extends BaseEntity {
    @Id
    @Column(name = "idProveedor", columnDefinition = "uuid")
    private UUID idProveedor;
    @Column(name = "nit", nullable = false, length = 150)
    private String nit;
    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;
    @Column(name = "direccion", length = 250)
    private String direccion;
    @Column(name = "telefono", length = 50)
    private String telefono;
    @Column(name = "correoElectronico", length = 150)
    private String correoElectronico;
    private List<OrdenCompra> ordenes = new ArrayList();

}
