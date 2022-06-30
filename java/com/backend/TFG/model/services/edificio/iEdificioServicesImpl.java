package com.backend.TFG.model.services.edificio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.TFG.model.dao.iClasesEdificioDao;
import com.backend.TFG.model.dao.iEdificioDao;
import com.backend.TFG.model.entity.ClasesEdificio;
import com.backend.TFG.model.entity.Edificio;

@Service
public class iEdificioServicesImpl implements iEdificioServices{

	@Autowired
	private iEdificioDao iEdificios;
	@Autowired
	private iClasesEdificioDao iClaseEdificio;
	
	@Override
	public List<Edificio> findEdificiosByCampus(Long id) {
		return iEdificios.findByCampusId(id);
	}

	@Override
	public Edificio findEdificioByNameAndCampus(String nombre, Long id) {
		Edificio edificio = iEdificios.findEdificioByNameAndCampus(nombre, id);
		return edificio;
	}

	@Override
	public Edificio modificarEdificio(Long campus, Long edificio, Integer planta) {
		ClasesEdificio clase=iClaseEdificio.findNumClases(campus, edificio, planta);
		int numClases = clase.getNumClases()+1;
		clase.setNumClases(numClases);
		iClaseEdificio.save(clase);
		
		
		Edificio edificioE=iEdificios.findNumClases(campus, edificio);
		Integer nClases = edificioE.getN_clases()+1;
		edificioE.setN_clases(nClases);
		return iEdificios.save(edificioE);
	}
	
}
