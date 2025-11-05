package com.mp.proveedor.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Clase base que contiene atributos comunes a todas las entidades.
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Column(name = "fechaCreacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "estado")
    private Boolean estado = true;
}
