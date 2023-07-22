package com.backend.TFG.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.TFG.Beans.BeanAula;
import com.backend.TFG.Beans.BeanRotos;
import com.backend.TFG.Beans.BeanRotosAux;
import com.backend.TFG.model.entity.AsientosRotos;
import com.backend.TFG.model.entity.Aula;
import com.backend.TFG.model.entity.Edificio;
import com.backend.TFG.model.entity.Estructura;
import com.backend.TFG.model.services.asientos_rotos.iRotosServices;
import com.backend.TFG.model.services.aula.iAulaServices;
import com.backend.TFG.model.services.campus.iCampusServices;
import com.backend.TFG.model.services.edificio.iEdificioServices;
import com.backend.TFG.model.services.estructura.iEstructuraServices;

import ClasesAux.Motivo;
import ClasesAux.Tipos;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/aulas")
public class AulasRestControllers implements AulasRest{

	@Autowired
	private iAulaServices aulasService;
	@Autowired
	private iEstructuraServices estructuraService;
	@Autowired
	private iCampusServices campusServices;
	@Autowired
	private iEdificioServices edificioServices;
	@Autowired
	private iRotosServices rotosService;
	
	
	//@GetMapping("/listar/{campus}/{id}")
	public List<Integer> aulasDistribucion(@PathVariable Long campus, @PathVariable Long id){
		List<Integer> distribucion=new ArrayList<>(); 
		Aula aula = aulasService.getDistribucionClase(id, campus).get(90);
		List<Integer> pasillo = damePasillos(aula.getEstructura());
		
		for(int i=0; i<aula.getColumna();i++)
			if(!contiene(i, pasillo))
				distribucion.add(aula.getFila());
			else
				distribucion.add(0);
		

		return distribucion;
	}
	
	//@PostMapping("/crearSR/{id}")
	public List<String> crearAulaServiceV2(@RequestBody BeanAula aulaBody, @PathVariable Long id) throws SQLException {
		
		List<String> lista = new ArrayList<String>();
		Aula aula = crearAula(aulaBody, id); 

		String nombre = aula.getEdificio().getNombre();
		
		lista.add(nombre);
		lista.add(Long.toString(aula.getCampus().getId()));
		lista.add(aula.getNombre());
		
		return lista;
	}
	
	
	//@PostMapping("/crear/{id}")
	public Aula crearAulaService(@RequestBody BeanAula aulaBody, @PathVariable Long id) throws SQLException {
		
		Aula aula = crearAula(aulaBody, id); 
	//	Aula aula = new Aula();

		//Se tiene que pasar desde el front un array con el numero de filas que hay en la BBDD
		// en este caso es 5, probablemente hay que crear mas columnas.

		if(!aulaBody.getNumAsientosRotos().isEmpty()) {
			AsientosRotos roto = null;
			for(BeanRotos rotos: aulaBody.getListaRotos()) {
				roto = new AsientosRotos();
				roto.setClase(aula);
				roto.setColumna(rotos.getColumna());
				roto.setFila(rotos.getFila()+1);
				roto.setMotivo(Motivo.ROTO);
				rotosService.crearRotos(roto);
				System.out.println("Se ha creado del asiento roto -> "+roto.toString());
			}	
		}
	
		return aula;
	}	

	//@GetMapping("/pintar/{nombreCampus}/{idCampus}/{nombreAula}")
	public List<List<Integer>> pintarClase(@PathVariable String nombreCampus, @PathVariable String idCampus, @PathVariable String nombreAula) throws SQLException{
		
		Integer filas,pasillo1,pasillo2,pasillo3,pasillo4,pasillo5;
		//Aula clase = aulasService.getDistribucionClase(idAula, idCampus).get(0);
		String nombreEdificioBueno=nombreCampus.replace("_", " ");
		Long id = Long.valueOf(idCampus);
		Edificio edificio = edificioServices.findEdificioByNameAndCampus(nombreEdificioBueno, id);
		Aula clase = aulasService.getAula(id, nombreAula, edificio.getId()).get(0);

		List<AsientosRotos> rotos = rotosService.findRotosByAula(clase.getId());
		Estructura estructura = estructuraService.getEstructura(clase.getEstructura()).get();
		List<List<Integer>> claseList = new ArrayList<>();
		List<Integer> lista = new ArrayList<>();
		
		filas= clase.getFila();
		pasillo1=estructura.getNfilapasillo1();
		pasillo2=estructura.getNfilapasillo2();		
		pasillo3=estructura.getNfilapasillo3();
		pasillo4=estructura.getNfilapasillo4();
		pasillo5=estructura.getNfilapasillo5();

		lista = dameEstructura(pasillo1, pasillo2, pasillo3, pasillo4, pasillo5);

		List<Integer> tipo = new ArrayList<>();
		if(clase.getTipo_clase() == Tipos.NORMAL) {
			tipo.add(0);
		} else {
			tipo.add(1);
		}
		claseList.add(tipo);
		
		List<Integer> listaFinal = null;
		for (int j=0; j<filas;j++) {
			listaFinal = new ArrayList<>();
			listaFinal=lista;
			claseList.add(listaFinal);
			listaFinal=null;
		}
		
		for(AsientosRotos roto: rotos) {
			Integer indiceArray = roto.getFila()-1;
			List<Integer> claseListAux = claseList.get(indiceArray);
			
			Integer columnaAux = dameColumna(roto.getColumna()-1, pasillo1, pasillo2, pasillo3, pasillo4, pasillo5);
			Integer columna = roto.getColumna()-1 + columnaAux;
			
			claseListAux = rellenaRotos(claseListAux, columna);
			claseList = rellenaArray(claseList, claseListAux, indiceArray);
		}
		
		return claseList;
	}
	
	//@PostMapping("/modificarrotos/{nombreEdificio}/{campusId}/{nombreClase}")
	public void modificarRotos(@RequestBody List<List<BeanRotosAux>> rotos, @PathVariable String nombreEdificio, @PathVariable Long campusId, @PathVariable Long nombreClase) throws SQLException {
		
		Long id = Long.valueOf(campusId);
		String nombreAula = String.valueOf(nombreClase);
		Edificio edificio = edificioServices.findEdificioByNameAndCampus(nombreEdificio, id);
		Aula clase = aulasService.getAula(id, nombreAula, edificio.getId()).get(0);
				
		if(rotos != null && !rotos.isEmpty()) {
			agregarRotos(rotos.get(0), clase);
			eliminarRotos(rotos.get(1), clase);
		}
	}
	
	private Aula crearAula(BeanAula aulaBody, Long id) throws SQLException {

		Aula aula = new Aula();
		Estructura estructura = rellenarEstructura(new Estructura(), aulaBody.getListaFilas());
		try {

			aula.setColumna(Integer.valueOf(aulaBody.getNumColumnas()));
			aula.setFila(Integer.valueOf(aulaBody.getFila()));
			aula.setPlanta(Integer.valueOf(aulaBody.getPlanta()));
			aula.setCampus(campusServices.findCampusById(id));
			aula.setTipo_clase(buscarTipo(aulaBody.getTipoAula()));
			aula.setEdificio(edificioServices.findEdificioByNameAndCampus(aulaBody.getEdificio(), id));
			aula.setEstructura(estructuraService.crearEstructura(estructura));
			aula.setNombre(rellenaNombre("", 0, aula.getCampus().getId(), aula.getEdificio().getId(),aula.getPlanta()));
	
			if(aulaBody.getNumAsientosRotos().isEmpty()) {
				aula.setNumAsientosRotos(0);
			} else {
				aula.setNumAsientosRotos(Integer.valueOf(aulaBody.getNumAsientosRotos()));
			}
			
			aulasService.crearAula(aula);
			
			Edificio edificio = edificioServices.modificarEdificio(aula.getCampus().getId(), aula.getEdificio().getId(),aula.getPlanta());
			System.out.println(edificio.toString());
			
			return aula;

		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error al crear la clase en BBDD");
		}
	}

	private Tipos buscarTipo(String tipoAula) {
		if(tipoAula.equalsIgnoreCase("CURVA"))
			return Tipos.ESPECIAL;
		else
			return Tipos.NORMAL;
	}
	
	private void agregarRotos(List<BeanRotosAux> lista, Aula clase) {
		
		if(lista!=null && !lista.isEmpty()) {
			AsientosRotos asiento = null;
			for(BeanRotosAux rotosAux: lista) {
				asiento = new AsientosRotos();
				asiento.setClase(clase);
				asiento.setColumna(getColumnas(rotosAux.getColumna(), clase));
				asiento.setFila(rotosAux.getFila()+1);
				asiento.setMotivo(Motivo.ROTO);
				rotosService.crearRotos(asiento);
				System.out.println("Se ha creado del asiento roto -> "+asiento.toString());
				
			}
		}
	}
	
	private Integer getColumnas(int columna, Aula clase) {
		Estructura estructura = estructuraService.findEstructuraById(clase.getEstructura().getId());
		Integer fila=estructura.getNfilapasillo1();
		if(columna <= fila)
			return columna+1;
		
		fila = fila + estructura.getNfilapasillo2();
		
		if(columna <= fila)
			return columna;
		
		fila = fila + estructura.getNfilapasillo3();
		columna--;
		
		if(columna <= fila)
			return columna;
		
		fila = fila + estructura.getNfilapasillo4();
		columna--;

		if(columna <= fila)
			return columna;

		return columna-1;

	}

	private void eliminarRotos(List<BeanRotosAux> lista, Aula clase) {
		
		if(lista!=null && !lista.isEmpty()) {
			for(BeanRotosAux rotosAux: lista) {
				AsientosRotos roto = rotosService.findRotosByAulaAndFilaAndColumna(clase.getId(), getColumnas(rotosAux.getColumna(), clase), rotosAux.getFila()+1).get(0);
				rotosService.deleteAsiento(roto.getId());
				System.out.println("Se ha eliminado el asiento roto -> "+roto.toString());
			}
		}
		
	}

	private List<Integer> dameEstructura(Integer pasillo1, Integer pasillo2, Integer pasillo3,
			Integer pasillo4, Integer pasillo5) {

		List<Integer> lista = new ArrayList<Integer>();
		
		if (pasillo1 != 0)
			for (int i=0; i<pasillo1;i++) 
				lista.add(0);
		if (pasillo2 != 0) {
			lista.add(3);
			for (int i=0; i<pasillo2;i++) 
				lista.add(0);
		}
		if (pasillo3 != 0) {
			lista.add(3);
			for (int i=0; i<pasillo3;i++) 
				lista.add(0);
		}
		if (pasillo4 != 0) {
			lista.add(3);
			for (int i=0; i<pasillo4;i++) 
				lista.add(0);
		}
		if (pasillo5 != 0){
			lista.add(3);
			for (int i=0; i<pasillo5;i++) 
				lista.add(0);
		}

		return lista;
	}

	private List<Integer> rellenaRotos(List<Integer> prueba2, Integer columna) {
		
		List<Integer> prueba=new ArrayList<Integer>();
		Boolean pasillo=false, buenos=false;
		
		for(int i=0;i<prueba2.size();i++)
			if((i==columna || pasillo) && !buenos) {
				if(prueba2.get(i) == 3) {
					pasillo=true;
					prueba.add(prueba2.get(i));
				}else {
					pasillo=false;
					buenos=false;
					prueba.add(1);	
				}
			}
			else
				prueba.add(prueba2.get(i));

		return prueba;
	}

	private List<List<Integer>> rellenaArray(List<List<Integer>> prueba, List<Integer> prueba2, Integer indiceArray) {
		
		List<List<Integer>> listaFinal = new ArrayList<>();
		for(int i=0;i< prueba.size();i++) {
			if(i==indiceArray)
				listaFinal.add(prueba2);
			else
				listaFinal.add(prueba.get(i));
		}
		
		return listaFinal;
	}

	private Integer dameColumna(Integer columna, Integer pasillo1, Integer pasillo2, Integer pasillo3, Integer pasillo4,Integer pasillo5) {
		
		if(columna<pasillo1)
			return 0;
		else if(columna<pasillo1+pasillo2)
			return 1;
		else if(columna<pasillo1+pasillo2+pasillo3)
			return 2;
		else if(columna<pasillo1+pasillo2+pasillo3+pasillo4)
			return 3;
		else
			return 4;
		
	}

	// Devuelve el nombre de la clase que vamos a insertar
	private String rellenaNombre(String nombreString, int nombre, Long campus, Long edificio, Integer planta) throws SQLException {

		nombreString=aulasService.maxAula(campus, edificio, planta);
		if(nombreString.length() <= 3) {
			nombre=Integer.valueOf(nombreString)+1;
			nombreString=String.valueOf(nombre);
		}else {
			//nombreString= nombreString + "2";
			String numero = nombreString.substring(0, 3);
			Integer num = Integer.valueOf(numero) + 1;
			nombreString = String.valueOf(num) + "G";
		}
		
		return nombreString;
	}

	// Inserta la estructura que va a tener la clase a insertar
	private Estructura rellenarEstructura(Estructura estructura, Integer[] listaFilas) {

		if((listaFilas[0] != 0 || listaFilas[0] != null) && listaFilas.length >= 1)
			estructura.setNfilapasillo1(listaFilas[0]);
		else
			estructura.setNfilapasillo1(0);
		
		if((listaFilas[1] != 0 || listaFilas[1] != null) && listaFilas.length >= 2)
			estructura.setNfilapasillo2(listaFilas[1]);
		else
			estructura.setNfilapasillo2(0);
		
		if((listaFilas[2] != 0 || listaFilas[2] != null) && listaFilas.length >= 3)
			estructura.setNfilapasillo3(listaFilas[2]);
		else
			estructura.setNfilapasillo3(0);
		
		if((listaFilas[3] != 0 || listaFilas[3] != null) && listaFilas.length >= 4)
			estructura.setNfilapasillo4(listaFilas[3]);
		else
			estructura.setNfilapasillo4(0);
		
		if((listaFilas[4] != 0 || listaFilas[4] != null) && listaFilas.length >= 5)
			estructura.setNfilapasillo5(listaFilas[4]);
		else
			estructura.setNfilapasillo5(0);

		return estructura;
}
	
	private boolean contiene(int i, List<Integer> pasillo) {
		
		for(Integer in: pasillo)
			if(in==i)
				return true;
		
		return false;
	}

	private List<Integer> damePasillos(Estructura pasillo) {
		List<Integer> pasillos = new ArrayList<>();
		
		if(pasillo.getNfilapasillo1()!=0)
			pasillos.add(pasillo.getNfilapasillo1());
		else
			return pasillos;

		if(pasillo.getNfilapasillo2()!=0)
			pasillos.add(pasillo.getNfilapasillo2());
		else
			return pasillos;
		
		if(pasillo.getNfilapasillo3()!=0)
			pasillos.add(pasillo.getNfilapasillo3());
		else
			return pasillos;
		
		if(pasillo.getNfilapasillo4()!=0)
			pasillos.add(pasillo.getNfilapasillo4());
		else
			return pasillos;
		
		if(pasillo.getNfilapasillo5()!=0)
			pasillos.add(pasillo.getNfilapasillo5());
		else
			return pasillos;
		
		if(pasillo.getNfilapasillo1()!=0)
			pasillos.add(pasillo.getNfilapasillo1());
		else
			return pasillos;
		
		return pasillos;
	}
	
	
}
