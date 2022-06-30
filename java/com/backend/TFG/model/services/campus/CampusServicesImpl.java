package com.backend.TFG.model.services.campus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.TFG.model.dao.iCampusDao;
import com.backend.TFG.model.dao.iClasesEdificioDao;
import com.backend.TFG.model.entity.Campus;
import com.backend.TFG.model.entity.ClasesEdificio;

@Service
public class CampusServicesImpl implements iCampusServices{

	@Autowired
	private iCampusDao iCampus;
	@Autowired
	private iClasesEdificioDao iClasesEdificio;

	@Override
	public List<String> findAllNames() {
		return iCampus.findAllNames();
	}

	@Override
	public List<ClasesEdificio> findByCampus(Long id) {
		return iClasesEdificio.findByCampusId(id);
	}

	@Override
	public Campus findCampusById(Long id) {
		return iCampus.findById(id).get();
	}

}
