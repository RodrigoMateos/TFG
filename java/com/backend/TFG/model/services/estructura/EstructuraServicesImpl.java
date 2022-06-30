package com.backend.TFG.model.services.estructura;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.TFG.model.dao.iEstructuraDao;
import com.backend.TFG.model.entity.Estructura;

@Service
public class EstructuraServicesImpl implements iEstructuraServices{
	
	@Autowired
	private iEstructuraDao iEstructuraDao;

	@Override
	public Optional<Estructura> getEstructura(Estructura estructura) {
		return iEstructuraDao.findById(estructura.getId());
	}
	
	@Override
	public Estructura findEstructuraById(Long id) {
		return iEstructuraDao.findById(id).orElse(null);
	}

	@Override
	public Estructura crearEstructura(Estructura estructura) {
		return iEstructuraDao.save(estructura);
	}


}
