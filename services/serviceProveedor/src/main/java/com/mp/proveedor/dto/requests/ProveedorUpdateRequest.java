package com.mp.proveedor.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProveedorUpdateRequest(
        @NotBlank @Size(max = 150) String nit,
        @NotBlank @Size(max = 250) String nombre,
        @Size(max = 250) String direccion,
        @Size(max = 50) String telefono,
        @Size(max = 150) @Email(message = "correo inválido") String correoElectronico) {
}
