package com.backend.TFG.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.TFG.Beans.BeanAula;
import com.backend.TFG.Beans.BeanRotosAux;
import com.backend.TFG.model.entity.Aula;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/aulas")
public interface AulasRest {

	@GetMapping("/listar/{campus}/{id}")
	@Operation(summary = "Devuelve la lista de todos los usuarios registrados en el sistema.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de usuarios en el sistema", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }),
		@ApiResponse(responseCode = "400", description = "Peticion invalida o incorrecta", content = @Content),
		@ApiResponse(responseCode = "404", description = "Host no encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
	public List<Integer> aulasDistribucion(@PathVariable Long campus, @PathVariable Long id);
	
	@PostMapping("/crear-aula/{id}/v2")
	@Operation(summary = "Crea nueva aula.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Crea nuevo aula y la retorna", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }),
		@ApiResponse(responseCode = "400", description = "Peticion invalida o incorrecta", content = @Content),
		@ApiResponse(responseCode = "404", description = "Host no encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
	public List<String> crearAulaServiceV2(@RequestBody BeanAula aulaBody, @PathVariable Long id) throws SQLException;
	
	@PostMapping("/crear-aula/{id}/v1")
	@Operation(summary = "Crea nueva aula.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Crea nuevo aula y la retorna", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }),
		@ApiResponse(responseCode = "400", description = "Peticion invalida o incorrecta", content = @Content),
		@ApiResponse(responseCode = "404", description = "Host no encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
	public Aula crearAulaService(@RequestBody BeanAula aulaBody, @PathVariable Long id) throws SQLException;
	
	@GetMapping("/pintar-aula/{nombreCampus}/{idCampus}/{nombreAula}/v1")
	@Operation(summary = "Devuelve la estructura del aula requerida.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Estructura del aula a pintar.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }),
		@ApiResponse(responseCode = "400", description = "Peticion invalida o incorrecta", content = @Content),
		@ApiResponse(responseCode = "404", description = "Host no encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
	public List<List<Integer>> pintarClase(@PathVariable String nombreCampus, @PathVariable String idCampus, @PathVariable String nombreAula) throws SQLException;
	
	@PostMapping("/modificar-rotos/{nombreEdificio}/{campusId}/{nombreClase}/v1")
	@Operation(summary = "Inserta la lista de asientos rotos.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de los nuevos asientos rotos del aula", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)) }),
		@ApiResponse(responseCode = "400", description = "Peticion invalida o incorrecta", content = @Content),
		@ApiResponse(responseCode = "404", description = "Host no encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
	public void modificarRotos(@RequestBody List<List<BeanRotosAux>> rotos, @PathVariable String nombreEdificio, @PathVariable Long campusId, @PathVariable Long nombreClase) throws SQLException;

	
	
}
