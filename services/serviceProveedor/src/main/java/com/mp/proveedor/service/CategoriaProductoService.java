package com.mp.proveedor.service;

import com.mp.proveedor.domain.entities.CategoriaProducto;
import com.mp.proveedor.dto.requests.CategoriaProductoRequest;
import com.mp.proveedor.dto.requests.CategoriaProductoUpdateRequest;
import com.mp.proveedor.dto.response.CategoriaProductoResponse;
import com.mp.proveedor.exception.BadRequestException;
import com.mp.proveedor.exception.NotFoundException;
import com.mp.proveedor.repository.CategoriaProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaProductoService implements ICategoriaProductoService {

    private final CategoriaProductoRepository repo;

    @Override
    public CategoriaProductoResponse crear(CategoriaProductoRequest req) {
        if (repo.existsByDescripcionIgnoreCase(req.descripcion())) {
            throw new BadRequestException("Ya existe una categoría con la descripción: " + req.descripcion());
        }

        CategoriaProducto cat = CategoriaProducto.builder()
                .idCategoria(UUID.randomUUID())
                .descripcion(req.descripcion())
                .build();
        cat.setEstado(true);

        var saved = repo.saveAndFlush(cat);
        return toResponse(saved);
    }

    @Override
    public CategoriaProductoResponse editar(UUID idCategoria, CategoriaProductoUpdateRequest req) {
        CategoriaProducto cat = repo.findById(idCategoria)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + idCategoria));

        if (!cat.getDescripcion().equalsIgnoreCase(req.descripcion())
                && repo.existsByDescripcionIgnoreCaseAndIdCategoriaNot(req.descripcion(), idCategoria)) {
            throw new BadRequestException("Ya existe una categoría con la descripción: " + req.descripcion());
        }

        cat.setDescripcion(req.descripcion());
        var saved = repo.saveAndFlush(cat);
        return toResponse(saved);
    }

    @Override
    public void desactivar(UUID idCategoria) {
        CategoriaProducto cat = repo.findById(idCategoria)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + idCategoria));
        cat.setEstado(false);
        repo.saveAndFlush(cat);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaProductoResponse obtener(UUID idCategoria) {
        return repo.findById(idCategoria)
                .map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + idCategoria));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaProductoResponse> listar(Boolean soloActivas, String filtroDescripcion) {
        var list = repo.findAll();
        return list.stream()
                .filter(c -> soloActivas == null || !soloActivas || Boolean.TRUE.equals(c.getEstado()))
                .filter(c -> filtroDescripcion == null || c.getDescripcion() != null &&
                        c.getDescripcion().toLowerCase().contains(filtroDescripcion.toLowerCase()))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CategoriaProductoResponse toResponse(CategoriaProducto c) {
        return new CategoriaProductoResponse(
                c.getIdCategoria(),
                c.getDescripcion(),
                c.getEstado()
        );
    }
}
