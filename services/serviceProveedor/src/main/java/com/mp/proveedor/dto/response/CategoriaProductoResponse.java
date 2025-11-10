package com.mp.proveedor.dto.response;
import java.util.UUID;

public record CategoriaProductoResponse(
        UUID idCategoria,
        String descripcion,
        Boolean estado
) {}