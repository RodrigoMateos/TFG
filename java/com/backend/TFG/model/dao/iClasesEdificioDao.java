package com.backend.TFG.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.backend.TFG.model.entity.Campus;
import com.backend.TFG.model.entity.ClasesEdificio;

public interface iClasesEdificioDao extends CrudRepository<ClasesEdificio, Long>{

	@Query(value="SELECT * FROM clases_edificio WHERE campus_id= :id", nativeQuery = true)
	List<ClasesEdificio> findByCampusId(@Param("id") Long id);

	@Query(value="SELECT * FROM clases_edificio WHERE campus_id= :campus AND planta= :planta AND edificio_id= :edificio", nativeQuery = true)
	ClasesEdificio findNumClases(Long campus, Long edificio, Integer planta);
	
	@Query(value="UPDATE clases_edificio SET num_clases= :nClase WHERE campus_id= :campus AND planta= :planta AND edificio_id= :edificio", nativeQuery = true)
	void updateClaseEdificio(Long campus, Long edificio, Integer planta, Integer nClase);

	List<ClasesEdificio> findByCampus(Campus campus);


	
}
