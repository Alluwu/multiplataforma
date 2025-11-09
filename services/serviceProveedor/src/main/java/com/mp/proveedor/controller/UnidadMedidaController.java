package com.mp.proveedor.controller;

import com.mp.proveedor.domain.entities.UnidadMedida;
import com.mp.proveedor.dto.requests.UnidadMedidaRequest;
import com.mp.proveedor.dto.requests.UnidadMedidaUpdateRequest;
import com.mp.proveedor.dto.response.UnidadMedidaResponse;
import com.mp.proveedor.service.IUnidadMedidaService;
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

@RestController
@RequestMapping(value = "/unidades-medida", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Unidades de Medida", description = "CRUD de unidades de medida")
public class UnidadMedidaController {

    private final IUnidadMedidaService service;

    @Operation(summary = "Crear unidad de medida", description = "Crea una nueva unidad de medida", responses = {
            @ApiResponse(responseCode = "201", description = "Creado", content = @Content(schema = @Schema(implementation = UnidadMedidaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnidadMedidaResponse> crear(
            @Valid @RequestBody UnidadMedidaRequest req) {
        var res = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @Operation(summary = "Editar unidad de medida", description = "Actualiza el nombre de una unidad existente", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UnidadMedidaResponse.class))),
            @ApiResponse(responseCode = "404", description = "No encontrada", content = @Content)
    })
    @PutMapping(value = "/{idUnidad}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnidadMedidaResponse> editar(
            @Parameter(description = "ID de la unidad (ej. UND)", required = true, example = "UND") @PathVariable("idUnidad") String idUnidad,
            @Valid @RequestBody UnidadMedidaUpdateRequest req) {
        return ResponseEntity.ok(service.editar(idUnidad, req));
    }

    @Operation(summary = "Desactivar activar unidad de medida", description = "Marca la unidad como inactiva o activa", responses = {
            @ApiResponse(responseCode = "204", description = "Sin contenido"),
            @ApiResponse(responseCode = "404", description = "No encontrada", content = @Content)
    })
    @PatchMapping("/{idUnidad}/desactivar-activar")
    public ResponseEntity<Void> desactivar(
            @Parameter(description = "ID de la unidad (ej. UND)", required = true, example = "UND") @PathVariable("idUnidad") String idUnidad) {
        service.desactivar(idUnidad);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar unidades de medida", description = "Devuelve todas las unidades; si `soloActivas=true`, filtra solo activas")

    @GetMapping
    public ResponseEntity<List<UnidadMedida>> listar(
            @RequestParam(name = "soloActivas", required = false) Boolean soloActivas) {
        return ResponseEntity.ok(service.listar(soloActivas));
    }
}
