package com.backend.TFG.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.TFG.Beans.BeanValor;
import com.backend.TFG.model.entity.Campus;
import com.backend.TFG.model.entity.Edificio;

@RequestMapping("/campus")
public interface CampusRest {

	@GetMapping("/listar")
	public List<String> dameCampus();
	
	@GetMapping("/{id}")
	public Campus dameCampusById(@PathVariable Long id);
	
	@GetMapping("/listado/{id}")
	public List<BeanValor> dameCampusCompleto(@PathVariable Long id);
	
	@GetMapping("listadoNombre/{id}")
	public List<String> findNombreEdificiosByCampus(@PathVariable Long id);
	
	@GetMapping("E/{id}")
	public List<Edificio> findEdificiosByCampus(@PathVariable Long id);
}
