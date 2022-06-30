package com.backend.TFG.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.backend.TFG.model.entity.Edificio;

public interface iEdificioDao extends CrudRepository<Edificio, Long>{

	Optional<Edificio> findById(Long id);
	
	@Query(value="SELECT * FROM edificio WHERE campus_id= :id", nativeQuery = true)
	List<Edificio> findByCampusId(@Param("id") Long id);
	
	@Query(value="SELECT * FROM edificio WHERE nombre= :nombre AND campus_id= :id",nativeQuery = true)
	Edificio findEdificioByNameAndCampus(@Param("nombre") String nombre, @Param("id") Long id);
	
	@Query(value="SELECT * FROM edificio WHERE id= :edificio AND campus_id= :campus",nativeQuery = true)
	Edificio findNumClases(@Param("campus") Long campus, @Param("edificio") Long edificio);

	@Query(value="UPDATE edificio SET n_clases= :nClases WHERE id= :edificio AND  campus_id= :campus",nativeQuery = true)
	Edificio updateClases(@Param("campus") Long campus, @Param("campus") Long edificio, Integer nClases);
}
