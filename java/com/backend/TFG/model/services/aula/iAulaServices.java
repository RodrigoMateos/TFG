package com.backend.TFG.model.services.aula;

import java.util.List;

import com.backend.TFG.model.entity.Aula;

public interface iAulaServices {

	List<Aula> getDistribucionClase(Long id, Long campus);
	Aula crearAula(Aula aula);
	String maxAula(Long campus, Long edificio, Integer planta);
	List<Aula> getAula(Long campus, String nombre, Long idEdificio);
	List<String> getClasesByEdificioAndPlanta(Long edificio, int planta);
	
}
