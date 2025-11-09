package com.mp.proveedor.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UnidadMedidaRequest(
    @NotBlank @Size(max = 50) String idUnidad,
    @NotBlank @Size(max = 100) String nombre
) {} 