package com.backend.TFG.model.services.edificio;

import java.util.List;

import com.backend.TFG.model.entity.Edificio;

public interface iEdificioServices {

	List<Edificio> findEdificiosByCampus(Long id);
	Edificio findEdificioByNameAndCampus(String nombre, Long id);
	Edificio modificarEdificio(Long campus, Long edificio, Integer planta);
}
