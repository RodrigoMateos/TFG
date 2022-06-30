package com.backend.TFG.model.services.aula;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.TFG.model.dao.iAulaDao;
import com.backend.TFG.model.entity.Aula;


@Service
public class AulaServicesImpl implements iAulaServices{

	@Autowired
	private iAulaDao iAula;
	

	@Override
	public List<Aula> getDistribucionClase(Long id, Long campus) {
		return  iAula.findAulaByIdAndCampus(id, campus);
	}


	@Override
	public Aula crearAula(Aula aula) {
		return iAula.save(aula);
	}


	@Override
	public String maxAula(Long campus, Long edificio, Integer planta) {
		String clase = iAula.findMax(edificio, campus, planta);
		if (clase == null || clase.isEmpty())
			return String.valueOf(planta*100);
		return clase;
	}


	@Override
	public List<Aula> getAula(Long campus, String nombre, Long idEdificio) {
		
		List<Aula> aula = iAula.findAulaByNameAndCampus(campus, nombre, idEdificio);
		
		return aula;
	}


	@Override
	public List<String> getClasesByEdificioAndPlanta(Long edificio, int planta) {
		List<String> aulas = iAula.getClasesByEdificioAndPlanta(edificio, planta);
		return aulas;
	}
	
}
