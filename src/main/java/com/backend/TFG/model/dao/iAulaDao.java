package com.backend.TFG.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.backend.TFG.model.entity.Aula;

public interface iAulaDao extends CrudRepository<Aula, Long>{
	
	@Query(value="SELECT * FROM aula WHERE id = :id AND campus_id= :campus", nativeQuery = true)
	List<Aula> findAulaByIdAndCampus(@Param("id") Long id, @Param("campus") Long campus);	
	
	@Query(value="SELECT MAX(nombre) FROM aula WHERE edificio_id = :edificio AND campus_id= :campus AND planta= :planta", nativeQuery = true)
	String findMax(@Param("edificio") Long edificio, @Param("campus") Long campus, @Param("planta") Integer planta);	

	@Query(value="SELECT * FROM aula WHERE campus_id= :campus AND nombre= :nombre AND edificio_id= :idEdificio", nativeQuery = true)
	List<Aula> findAulaByNameAndCampus(@Param("campus") Long campus, @Param("nombre") String nombre, @Param("idEdificio") Long idEdificio);

	@Query(value="SELECT nombre FROM aula WHERE planta= :planta AND edificio_id= :edificio", nativeQuery = true)
	List<String> getClasesByEdificioAndPlanta(Long edificio, int planta);
}
