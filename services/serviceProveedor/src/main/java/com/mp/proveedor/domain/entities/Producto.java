package com.mp.proveedor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends BaseEntity {
    @Id
    @Column(name = "idProducto", length = 150)
    private String idProducto;
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
    private CategoriaProducto categoria;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUnidadMedida", referencedColumnName = "idUnidad")
    private UnidadMedida unidadMedida;
    @Column(name = "precioCompra", precision = 18, scale = 2)
    private BigDecimal precioCompra;
    @Column(name = "precioCompraSinIVA", precision = 18, scale = 2)
    private BigDecimal precioCompraSinIVA;
    @Column(name = "precioVenta", precision = 18, scale = 2)
    private BigDecimal precioVenta;
    @Column(name = "precioVentaSinIVA", precision = 18, scale = 2)
    private BigDecimal precioVentaSinIVA;

}
