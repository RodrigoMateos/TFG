package com.backend.TFG.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ClasesAux.Motivo;

@Entity
@Table(name = "rotos")
public class AsientosRotos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer fila, columna;
	private Motivo motivo;
	@OneToOne
	private Aula clase;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getFila() {
		return fila;
	}
	public void setFila(Integer fila) {
		this.fila = fila;
	}
	public Integer getColumna() {
		return columna;
	}
	public void setColumna(Integer columna) {
		this.columna = columna;
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	public Aula getClase() {
		return clase;
	}
	public void setClase(Aula clase) {
		this.clase = clase;
	}
	@Override
	public String toString() {
		return "AsientosRotos [id=" + id + ", fila=" + fila + ", columna=" + columna + ", motivo=" + motivo + ", clase= { "
				+ "\n	id:"+ clase.getId() + ",\n"
				+ "	id:"+ clase.getId() +",\n"
				+ "	Nombre:"+ clase.getNombre() +",\n"
				+ "	Campus:"+ clase.getCampus().getNombre() +",\n"
				+ "	Edificio:"+ clase.getEdificio() +",\n"
				+ "	Planta:"+ clase.getPlanta() +",\n"
				+ "	Estructura:"+ clase.getEstructura() + "}]";	
	}
	
}
