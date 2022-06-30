package com.backend.TFG.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.backend.TFG.model.entity.AsientosRotos;

public interface iRotosDao extends CrudRepository<AsientosRotos, Long>{

	@Query(value="SELECT * FROM rotos WHERE clase_id = :clase", nativeQuery = true)
	List<AsientosRotos> findRotosByClase(@Param("clase") Long clase);	
	
	@Query(value="DELETE FROM rotos WHERE clase_id = :clase AND columna = :columna AND fila = :fila", nativeQuery = true)
	void deleteRoto(@Param("clase") Long clase, @Param("columna") Integer columna, @Param("fila") Integer fila);	
	
	@Query(value="SELECT * FROM rotos WHERE clase_id = :clase AND columna = :columna AND fila = :fila", nativeQuery = true)
	List<AsientosRotos> findRotosByAulaAndFilaAndColumna(@Param("clase") Long clase, @Param("columna") Integer columna, @Param("fila") Integer fila);	
	
}
 