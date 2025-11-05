package com.mp.proveedor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ordenCompraDetalle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenCompraDetalle extends BaseEntity {
    @Id
    @Column(name = "idOrdenCompraDetalle", columnDefinition = "uuid")
    private UUID idOrdenCompraDetalle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idOrdenCompra", referencedColumnName = "idOrdenCompra")
    private OrdenCompra ordenCompra;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    private Producto producto;

    @Column(name = "cantidad", precision = 18, scale = 3)
    private BigDecimal cantidad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idUnidadMedida", referencedColumnName = "idUnidad")
    private UnidadMedida unidadMedida;

    @Column(name = "totalCompra", precision = 18, scale = 2)
    private BigDecimal totalCompra;

    @Column(name = "PrecioUnidad", precision = 18, scale = 2)
    private BigDecimal precioUnidad;

}
