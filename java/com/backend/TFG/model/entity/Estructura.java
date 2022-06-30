package com.backend.TFG.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estructura {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int nfilapasillo1, nfilapasillo2, nfilapasillo3, nfilapasillo4, nfilapasillo5;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNfilapasillo1() {
		return nfilapasillo1;
	}
	public void setNfilapasillo1(int nfilapasillo1) {
		this.nfilapasillo1 = nfilapasillo1;
	}
	public int getNfilapasillo2() {
		return nfilapasillo2;
	}
	public void setNfilapasillo2(int nfilapasillo2) {
		this.nfilapasillo2 = nfilapasillo2;
	}
	public int getNfilapasillo3() {
		return nfilapasillo3;
	}
	public void setNfilapasillo3(int nfilapasillo3) {
		this.nfilapasillo3 = nfilapasillo3;
	}
	public int getNfilapasillo4() {
		return nfilapasillo4;
	}
	public void setNfilapasillo4(int nfilapasillo4) {
		this.nfilapasillo4 = nfilapasillo4;
	}
	public int getNfilapasillo5() {
		return nfilapasillo5;
	}
	public void setNfilapasillo5(int nfilapasillo5) {
		this.nfilapasillo5 = nfilapasillo5;
	}
}
