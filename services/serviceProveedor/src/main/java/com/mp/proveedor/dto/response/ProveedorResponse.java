package com.mp.proveedor.dto.response;

import java.util.UUID;
public record ProveedorResponse(
        UUID idProveedor,
        String nit,
        String nombre,
        String direccion,
        String telefono,
        String correoElectronico,
        Boolean estado) {

}
