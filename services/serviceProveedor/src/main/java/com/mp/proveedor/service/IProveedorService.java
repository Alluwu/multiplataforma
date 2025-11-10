package com.mp.proveedor.service;

import com.mp.proveedor.dto.requests.ProveedorRequest;
import com.mp.proveedor.dto.requests.ProveedorUpdateRequest;
import com.mp.proveedor.dto.response.ProveedorResponse;

import java.util.List;
import java.util.UUID;

public interface IProveedorService {
   ProveedorResponse crear(ProveedorRequest request);
    ProveedorResponse editar(UUID idProveedor, ProveedorUpdateRequest request);
    void desactivar(UUID idProveedor);
    ProveedorResponse obtener(UUID idProveedor);
    List<ProveedorResponse> listar(Boolean soloActivos, String filtroNit, String filtroNombre); 
}
