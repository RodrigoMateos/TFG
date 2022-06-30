package com.backend.TFG.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.TFG.Beans.BeanPlantas;
import com.backend.TFG.Beans.BeanValor;
import com.backend.TFG.model.entity.Campus;
import com.backend.TFG.model.entity.ClasesEdificio;
import com.backend.TFG.model.entity.Edificio;
import com.backend.TFG.model.services.aula.iAulaServices;
import com.backend.TFG.model.services.campus.iCampusServices;
import com.backend.TFG.model.services.edificio.iEdificioServices;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/campus")
public class CampusRestControllers {

	@Autowired
	public iCampusServices campusServices;
	@Autowired
	public iEdificioServices edificioServices;
	@Autowired
	public iAulaServices aulasServices;
	
	@GetMapping("/listar")
	public List<String> dameCampus(){
		return campusServices.findAllNames();
	}
	
	@GetMapping("/{id}")
	public Campus dameCampusById(@PathVariable Long id) {
		return campusServices.findCampusById(id);
	}
	
	@GetMapping("/listado/{id}")
	public List<BeanValor> dameCampusCompleto(@PathVariable Long id){
		
		List<BeanPlantas> listaPlantaClases = new ArrayList<>();
		List<BeanValor> listaClases = new ArrayList<>();
		String edificioOld="";
		List<ClasesEdificio> prueba = campusServices.findByCampus(id);
		
		for(ClasesEdificio cl : prueba) {
			String edificio = cl.getNombre();
			List<String> clases = aulasServices.getClasesByEdificioAndPlanta(cl.getEdificio().getId(), cl.getPlanta());

			if(!edificio.equals(edificioOld) && !edificioOld.isEmpty()) {
				listaClases.add(new BeanValor(edificioOld, listaPlantaClases));
				listaPlantaClases = new ArrayList<>();
			}
			
			listaPlantaClases.add(new BeanPlantas(cl.getPlanta(), clases));
			edificioOld=edificio;
		}
		
		listaClases.add(new BeanValor(edificioOld, listaPlantaClases));
		
		return listaClases;
		
	}
	
	
	@GetMapping("listadoNombre/{id}")
	public List<String> findNombreEdificiosByCampus(@PathVariable Long id) {
		List<String> nombres = new ArrayList<>();
		
		for(Edificio edificio: edificioServices.findEdificiosByCampus(id))
			nombres.add(edificio.getNombre());
		
		return nombres;
	}
	
	@GetMapping("E/{id}")
	public List<Edificio> findEdificiosByCampus(@PathVariable Long id) {
		return edificioServices.findEdificiosByCampus(id);
	}

}

