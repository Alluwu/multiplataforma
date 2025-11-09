package com.mp.proveedor.service;

import com.mp.proveedor.domain.entities.UnidadMedida;
import com.mp.proveedor.dto.requests.UnidadMedidaRequest;
import com.mp.proveedor.dto.requests.UnidadMedidaUpdateRequest;
import com.mp.proveedor.dto.response.UnidadMedidaResponse;

import java.util.List;
public interface IUnidadMedidaService {
    UnidadMedidaResponse crear(UnidadMedidaRequest request);

    UnidadMedidaResponse editar(String idUnidad, UnidadMedidaUpdateRequest req);

    void desactivar(String idUnidad);

    List<UnidadMedida> listar(Boolean soloActivas);

}
