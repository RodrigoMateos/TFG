package com.backend.TFG.model.services.asientos_rotos;

import java.util.List;

import com.backend.TFG.model.entity.AsientosRotos;

public interface iRotosServices {

	List<AsientosRotos> findRotosByAula(Long id);
	List<AsientosRotos> findRotosByAulaAndFilaAndColumna(Long idClase, Integer columna, Integer fila);
	void crearRotos(AsientosRotos roto);
	void deleteAsiento(Long idRoto);
}
