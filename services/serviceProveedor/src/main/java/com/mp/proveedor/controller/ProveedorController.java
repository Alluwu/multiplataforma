package com.mp.proveedor.controller;

import com.mp.proveedor.dto.requests.ProveedorRequest;
import com.mp.proveedor.dto.requests.ProveedorUpdateRequest;
import com.mp.proveedor.dto.response.ProveedorResponse;
import com.mp.proveedor.service.IProveedorService;
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
@RequestMapping(value = "/proveedores", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Proveedores", description = "CRUD de proveedores")
public class ProveedorController {

    private final IProveedorService service;

    @Operation(
        summary = "Crear proveedor",
        responses = {
            @ApiResponse(responseCode = "201", description = "Creado",
                content = @Content(schema = @Schema(implementation = ProveedorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida / NIT duplicado", content = @Content)
        }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedorResponse> crear(@Valid @RequestBody ProveedorRequest req){
        var res = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @Operation(
        summary = "Editar proveedor",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(schema = @Schema(implementation = ProveedorResponse.class))),
            @ApiResponse(responseCode = "404", description = "No encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "NIT duplicado", content = @Content)
        }
    )
    @PutMapping(value = "/{idProveedor}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedorResponse> editar(
            @Parameter(description = "ID del proveedor (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable("idProveedor") UUID idProveedor,
            @Valid @RequestBody ProveedorUpdateRequest req){
        return ResponseEntity.ok(service.editar(idProveedor, req));
    }

    @Operation(
        summary = "Desactivar proveedor",
        description = "Marca el proveedor como inactivo (soft delete)",
        responses = {
            @ApiResponse(responseCode = "204", description = "Sin contenido"),
            @ApiResponse(responseCode = "404", description = "No encontrado", content = @Content)
        }
    )
    @PatchMapping("/{idProveedor}/desactivar")
    public ResponseEntity<Void> desactivar(
            @Parameter(description = "ID del proveedor (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable("idProveedor") UUID idProveedor){
        service.desactivar(idProveedor);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener proveedor por ID")
    @GetMapping("/{idProveedor}")
    public ResponseEntity<ProveedorResponse> obtener(
            @Parameter(description = "ID del proveedor (UUID)", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable("idProveedor") UUID idProveedor){
        return ResponseEntity.ok(service.obtener(idProveedor));
    }

    @Operation(summary = "Listar proveedores", description = "Usa filtros opcionales: ?soloActivos=true&filtroNit=CF&filtroNombre=tech")
    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> listar(
            @Parameter(description = "Si es true, devuelve solo activos", example = "true")
            @RequestParam(required = false) Boolean soloActivos,
            @Parameter(description = "Filtro por NIT (contiene)")
            @RequestParam(required = false) String filtroNit,
            @Parameter(description = "Filtro por nombre (contiene)")
            @RequestParam(required = false) String filtroNombre){
        return ResponseEntity.ok(service.listar(soloActivos, filtroNit, filtroNombre));
    }
}

