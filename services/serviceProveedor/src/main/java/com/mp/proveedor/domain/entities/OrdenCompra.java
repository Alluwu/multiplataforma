package com.mp.proveedor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "ordenCompra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenCompra {
    @Id
    @Column(name = "idOrdenCompra", columnDefinition = "uuid")
    private UUID idOrdenCompra;

    @Column(name = "numero", length = 50)
    private String numero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;

    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "total", precision = 18, scale = 2)
    private BigDecimal total;

    @Column(name = "anulado")
    private Boolean anulado;

    @Column(name = "fechaAnulacion")
    private LocalDateTime fechaAnulacion;

    @Column(name = "finalizado")
    private Boolean finalizado;

    @Column(name = "fechaFinalizacion")
    private LocalDateTime fechaFinalizacion;

    @Column(name = "pagado")
    private Boolean pagado;

    @Column(name = "fechaPago")
    private LocalDateTime fechaPago;

    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenCompraDetalle> detalles = new ArrayList<>();
}
