package com.backend.TFG.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nombre;
	private Integer n_clases;
	private Integer plantas;
	@ManyToOne
	private Campus campus;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Campus getCampus() {
		return campus;
	}
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	public Integer getN_clases() {
		return n_clases;
	}
	public void setN_clases(Integer n_clases) {
		this.n_clases = n_clases;
	}
	public Integer getPlantas() {
		return plantas;
	}
	public void setPlantas(Integer plantas) {
		this.plantas = plantas;
	}
	@Override
	public String toString() {
		return "Edificio [id=" + id + ", nombre=" + nombre + ", n_clases=" + n_clases + ", plantas=" + plantas
				+ ", campus=" + campus + "]";
	}
	
}
