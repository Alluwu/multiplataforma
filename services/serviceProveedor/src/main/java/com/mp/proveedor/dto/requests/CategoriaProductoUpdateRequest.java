package com.mp.proveedor.dto.requests;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record CategoriaProductoUpdateRequest(
     @NotBlank @Size(max = 150) String descripcion
) {
    
}
