package com.mp.proveedor.service;

import com.mp.proveedor.dto.requests.CategoriaProductoRequest;
import com.mp.proveedor.dto.requests.CategoriaProductoUpdateRequest;
import com.mp.proveedor.dto.response.CategoriaProductoResponse;

import java.util.List;
import java.util.UUID;

public interface ICategoriaProductoService {
    CategoriaProductoResponse crear(CategoriaProductoRequest req);
    CategoriaProductoResponse editar(UUID idCategoria, CategoriaProductoUpdateRequest req);
    void desactivar(UUID idCategoria);
    CategoriaProductoResponse obtener(UUID idCategoria);
    List<CategoriaProductoResponse> listar(Boolean soloActivas, String filtroDescripcion);
}

