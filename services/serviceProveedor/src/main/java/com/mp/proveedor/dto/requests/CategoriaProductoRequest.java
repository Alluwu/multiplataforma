package com.mp.proveedor.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaProductoRequest(
        @NotBlank @Size(max = 150) String descripcion
) {}
