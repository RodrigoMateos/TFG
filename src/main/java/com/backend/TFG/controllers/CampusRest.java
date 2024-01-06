package com.backend.TFG.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.TFG.Beans.BeanValor;
import com.backend.TFG.model.entity.Campus;
import com.backend.TFG.model.entity.Edificio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/campus")
public interface CampusRest {

	@GetMapping("/listar")
	public List<String> dameCampus();
	
	@GetMapping("cargar-campus/{id}/v1")
	@Operation(summary = "Lista de nombres de edificios en el campus.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de nombres de edificios del campus", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }),
		@ApiResponse(responseCode = "400", description = "Peticion invalida o incorrecta", content = @Content),
		@ApiResponse(responseCode = "404", description = "Host no encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
	public Campus dameCampusById(@PathVariable Long id);
	
	@GetMapping("/listar-campus/{id}/v1")
	@Operation(summary = "Lista de plantas disponibles de cada edificio del campus.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de plantas disponibles de cada edificio del campus", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }),
		@ApiResponse(responseCode = "400", description = "Peticion invalida o incorrecta", content = @Content),
		@ApiResponse(responseCode = "404", description = "Host no encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
	public List<BeanValor> dameCampusCompleto(@PathVariable Long id);
	
	@GetMapping("listar-edificio/{id}/v1")
	@Operation(summary = "Lista de nombres de edificios en el campus.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de nombres de edificios del campus", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }),
		@ApiResponse(responseCode = "400", description = "Peticion invalida o incorrecta", content = @Content),
		@ApiResponse(responseCode = "404", description = "Host no encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
	public List<String> findNombreEdificiosByCampus(@PathVariable Long id);
	
	@GetMapping("obtener-edificios/{id}/v1")
	@Operation(summary = "Lista de edificios en el campus.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de edificios del campus", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }),
		@ApiResponse(responseCode = "400", description = "Peticion invalida o incorrecta", content = @Content),
		@ApiResponse(responseCode = "404", description = "Host no encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
	public List<Edificio> findEdificiosByCampus(@PathVariable Long id);
}
