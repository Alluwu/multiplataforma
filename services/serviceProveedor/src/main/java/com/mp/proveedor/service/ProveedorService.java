package com.mp.proveedor.service;

import com.mp.proveedor.domain.entities.Proveedor;
import com.mp.proveedor.dto.requests.ProveedorRequest;
import com.mp.proveedor.dto.requests.ProveedorUpdateRequest;
import com.mp.proveedor.dto.response.ProveedorResponse;
import com.mp.proveedor.exception.BadRequestException;
import com.mp.proveedor.exception.NotFoundException;
import com.mp.proveedor.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProveedorService implements IProveedorService {

    private final ProveedorRepository repo;

    @Override
    public ProveedorResponse crear(ProveedorRequest req) {
        if (repo.existsByNitIgnoreCase(req.nit())) {
            throw new BadRequestException("Ya existe un proveedor con el NIT: " + req.nit());
        }

        Proveedor p = Proveedor.builder()
                .idProveedor(UUID.randomUUID()) 
                .nit(req.nit())
                .nombre(req.nombre())
                .direccion(req.direccion())
                .telefono(req.telefono())
                .correoElectronico(req.correoElectronico())
                .build();

        var saved = repo.saveAndFlush(p);
        return toResponse(saved);
    }

    @Override
    public ProveedorResponse editar(UUID idProveedor, ProveedorUpdateRequest req) {
        Proveedor p = repo.findById(idProveedor)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado: " + idProveedor));

        if (!p.getNit().equalsIgnoreCase(req.nit())
                && repo.existsByNitIgnoreCaseAndIdProveedorNot(req.nit(), idProveedor)) {
            throw new BadRequestException("Ya existe un proveedor con el NIT: " + req.nit());
        }

        p.setNit(req.nit());
        p.setNombre(req.nombre());
        p.setDireccion(req.direccion());
        p.setTelefono(req.telefono());
        p.setCorreoElectronico(req.correoElectronico());

        var saved = repo.saveAndFlush(p);
        return toResponse(saved);
    }

    @Override
    public void desactivar(UUID idProveedor) {
        Proveedor p = repo.findById(idProveedor)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado: " + idProveedor));
        p.setEstado(false);
        repo.saveAndFlush(p);
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorResponse obtener(UUID idProveedor) {
        return repo.findById(idProveedor)
                .map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado: " + idProveedor));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorResponse> listar(Boolean soloActivos, String filtroNit, String filtroNombre) {
        var todos = repo.findAll();

        return todos.stream()
                .filter(p -> soloActivos == null || !soloActivos || Boolean.TRUE.equals(p.getEstado()))
                .filter(p -> filtroNit == null || p.getNit() != null &&
                        p.getNit().toLowerCase().contains(filtroNit.toLowerCase()))
                .filter(p -> filtroNombre == null || p.getNombre() != null &&
                        p.getNombre().toLowerCase().contains(filtroNombre.toLowerCase()))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ProveedorResponse toResponse(Proveedor p) {
        return new ProveedorResponse(
                p.getIdProveedor(),
                p.getNit(),
                p.getNombre(),
                p.getDireccion(),
                p.getTelefono(),
                p.getCorreoElectronico(),
                p.getEstado());
    }
}
