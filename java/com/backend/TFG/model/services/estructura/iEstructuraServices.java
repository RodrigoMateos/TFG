package com.backend.TFG.model.services.estructura;

import java.util.Optional;

import com.backend.TFG.model.entity.Estructura;

public interface iEstructuraServices {

	Optional<Estructura> getEstructura(Estructura estructura);
	Estructura findEstructuraById(Long id);
	Estructura crearEstructura(Estructura estructura);
}
