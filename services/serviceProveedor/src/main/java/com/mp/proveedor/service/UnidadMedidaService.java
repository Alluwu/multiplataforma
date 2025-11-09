package com.mp.proveedor.service;

import com.mp.proveedor.domain.entities.UnidadMedida;
import com.mp.proveedor.dto.requests.*;
import com.mp.proveedor.dto.response.*;
import com.mp.proveedor.exception.BadRequestException;
import com.mp.proveedor.exception.NotFoundException;
import com.mp.proveedor.repository.UnidadMedidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadMedidaService implements IUnidadMedidaService {
    private final UnidadMedidaRepository repo;

    @Transactional
    public UnidadMedidaResponse crear(UnidadMedidaRequest request) {
        if (repo.existsById(request.idUnidad())) {
            throw new BadRequestException("La unidad con id '" + request.idUnidad() + "' ya existe");
        }

        UnidadMedida nuevo = new UnidadMedida();
        nuevo.setIdUnidad(request.idUnidad());
        nuevo.setNombre(request.nombre());
        nuevo.setEstado(true);
        var saved = repo.save(nuevo);
        return toResponse(saved);
    }

    @Transactional
    public UnidadMedidaResponse editar(String idUnidad, UnidadMedidaUpdateRequest req) {
        UnidadMedida um = repo.findById(idUnidad)
                .orElseThrow(() -> new NotFoundException("No existe unidad con id '" + idUnidad + "'"));
        um.setNombre(req.nombre());
        var saved = repo.save(um);
        return toResponse(saved);
    }

    @Transactional
    public void desactivar(String idUnidad) {
        UnidadMedida um = repo.findById(idUnidad)
                .orElseThrow(() -> new NotFoundException("No existe unidad con id '" + idUnidad + "'"));
        if (um.getEstado() == false) {
            um.setEstado(true);
        } else {
            um.setEstado(false);
        }
        repo.save(um);
    }

    @Transactional(readOnly = true)
    public List<UnidadMedida> listar(Boolean soloActivas) {
        return (soloActivas != null && soloActivas)
                ? repo.findAll().stream()
                        .filter(u -> Boolean.TRUE.equals(u.getEstado()))
                        .toList()
                : repo.findAll();
    }

    private UnidadMedidaResponse toResponse(UnidadMedida u) {
        return new UnidadMedidaResponse(u.getIdUnidad(), u.getNombre(), u.getEstado());
    }
}
