package com.backend.TFG.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.backend.TFG.model.entity.Campus;

public interface iCampusDao extends CrudRepository<Campus, Long>{

	@Query(value="SELECT nombre FROM campus", nativeQuery = true)
	List<String> findAllNames();
	
}