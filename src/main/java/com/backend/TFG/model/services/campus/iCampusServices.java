package com.backend.TFG.model.services.campus;

import java.util.List;

import com.backend.TFG.model.entity.Campus;
import com.backend.TFG.model.entity.ClasesEdificio;

public interface iCampusServices {
	
	List<String> findAllNames();
	Campus findCampusById(Long id);
	List<ClasesEdificio> findByCampus(Long id);
	
	
}
