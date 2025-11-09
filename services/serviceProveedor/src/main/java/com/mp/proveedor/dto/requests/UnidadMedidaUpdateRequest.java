package com.mp.proveedor.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UnidadMedidaUpdateRequest(
    @NotBlank @Size(max = 100) String nombre
) {
}
