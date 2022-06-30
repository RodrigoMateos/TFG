package com.backend.TFG.model.services.asientos_rotos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.TFG.model.dao.iRotosDao;
import com.backend.TFG.model.entity.AsientosRotos;

@Service
public class RotosServicesImpl implements iRotosServices{

	@Autowired
	private iRotosDao iRotos;
	

	@Override
	public List<AsientosRotos> findRotosByAula(Long id) {
		return iRotos.findRotosByClase(id);
	}


	@Override
	public void crearRotos(AsientosRotos roto) {
		iRotos.save(roto);
	}


	@Override
	public void deleteAsiento(Long id) {
		iRotos.deleteById(id);
	}


	@Override
	public List<AsientosRotos> findRotosByAulaAndFilaAndColumna(Long idClase, Integer columna, Integer fila) {
		return iRotos.findRotosByAulaAndFilaAndColumna(idClase, columna, fila);

	}
	
}
