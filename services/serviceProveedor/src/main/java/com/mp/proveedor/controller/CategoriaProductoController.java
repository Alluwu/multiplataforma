package com.mp.proveedor.controller;

import com.mp.proveedor.dto.requests.CategoriaProductoRequest;
import com.mp.proveedor.dto.requests.CategoriaProductoUpdateRequest;
import com.mp.proveedor.dto.response.CategoriaProductoResponse;
import com.mp.proveedor.service.ICategoriaProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/categorias-producto", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Categorías de Producto", description = "CRUD de categorías")
public class CategoriaProductoController {

    private final ICategoriaProductoService service;

    @Operation(
        summary = "Crear categoría",
        responses = {
            @ApiResponse(responseCode = "201", description = "Creado",
                content = @Content(schema = @Schema(implementation = CategoriaProductoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Descripción duplicada", content = @Content)
        }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaProductoResponse> crear(@Valid @RequestBody CategoriaProductoRequest req){
        var res = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @Operation(
        summary = "Editar categoría",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(schema = @Schema(implementation = CategoriaProductoResponse.class))),
            @ApiResponse(responseCode = "404", description = "No encontrada", content = @Content),
            @ApiResponse(responseCode = "400", description = "Descripción duplicada", content = @Content)
        }
    )
    @PutMapping(value = "/{idCategoria}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaProductoResponse> editar(
            @Parameter(description = "ID de la categoría (UUID)", required = true,
                       example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable("idCategoria") UUID idCategoria,
            @Valid @RequestBody CategoriaProductoUpdateRequest req){
        return ResponseEntity.ok(service.editar(idCategoria, req));
    }

    @Operation(
        summary = "Desactivar categoría (soft delete)",
        responses = {
            @ApiResponse(responseCode = "204", description = "Sin contenido"),
            @ApiResponse(responseCode = "404", description = "No encontrada", content = @Content)
        }
    )
    @PatchMapping("/{idCategoria}/desactivar")
    public ResponseEntity<Void> desactivar(
            @Parameter(description = "ID de la categoría (UUID)", required = true)
            @PathVariable("idCategoria") UUID idCategoria){
        service.desactivar(idCategoria);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener categoría por ID")
    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaProductoResponse> obtener(
            @Parameter(description = "ID de la categoría (UUID)", required = true)
            @PathVariable("idCategoria") UUID idCategoria){
        return ResponseEntity.ok(service.obtener(idCategoria));
    }

    @Operation(summary = "Listar categorías", description = "Filtros opcionales: ?soloActivas=true&filtroDescripcion=imp")
    @GetMapping
    public ResponseEntity<List<CategoriaProductoResponse>> listar(
            @Parameter(description = "Si es true, devuelve solo activas", example = "true")
            @RequestParam(required = false) Boolean soloActivas,
            @Parameter(description = "Filtro por descripción (contiene)")
            @RequestParam(required = false) String filtroDescripcion){
        return ResponseEntity.ok(service.listar(soloActivas, filtroDescripcion));
    }
}
