package com.backend.TFG.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.backend.TFG.model.entity.Estructura;

public interface iEstructuraDao extends CrudRepository<Estructura, Long> {

	@Query(value="SELECT * FROM estructura WHERE nfilapasillo1 = :getNfilapasillo1 AND nfilapasillo2 = :getNfilapasillo2 AND nfilapasillo3 = :getNfilapasillo3 AND nfilapasillo4 = :getNfilapasillo4 AND nfilapasillo5 = :getNfilapasillo5", nativeQuery = true)
	List<Estructura> findByAll(@Param("getNfilapasillo1") Integer getNfilapasillo1, @Param("getNfilapasillo2") Integer getNfilapasillo2, @Param("getNfilapasillo3") Integer getNfilapasillo3, @Param("getNfilapasillo4") Integer getNfilapasillo4, @Param("getNfilapasillo5") Integer getNfilapasillo5);
	
}
