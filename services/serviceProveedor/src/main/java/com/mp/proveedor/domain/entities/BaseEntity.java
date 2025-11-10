package com.mp.proveedor.domain.entities;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import java.time.LocalDateTime;


@MappedSuperclass
@Data
public abstract class BaseEntity {

   
    private LocalDateTime fechaCreacion = LocalDateTime.now();

 
    private Boolean estado = true;
}
