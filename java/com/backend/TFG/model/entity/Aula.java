package com.backend.TFG.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ClasesAux.Tipos;

@Entity
public class Aula {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nombre;
	@ManyToOne
	private Campus campus;
	@ManyToOne
	private Edificio edificio;
	private Integer fila, columna, numAsientosRotos,planta;
	private Tipos tipo_clase;
	@ManyToOne
	private Estructura estructura;
	
	
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
	public Integer getNumAsientosRotos() {
		return numAsientosRotos;
	}
	public void setNumAsientosRotos(Integer numAsientosRotos) {
		this.numAsientosRotos = numAsientosRotos;
	}
	public Tipos getTipo_clase() {
		return tipo_clase;
	}
	public void setTipo_clase(Tipos tipo_clase) {
		this.tipo_clase = tipo_clase;
	}
	public Campus getCampus() {
		return campus;
	}
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	public Edificio getEdificio() {
		return edificio;
	}
	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
	public Integer getPlanta() {
		return planta;
	}
	public void setPlanta(Integer planta) {
		this.planta = planta;
	}
	public Estructura getEstructura() {
		return estructura;
	}
	public void setEstructura(Estructura estructura) {
		this.estructura = estructura;
	}
	@Override
	public String toString() {
		return "Aula [id=" + id + ", nombre=" + nombre + ", campus=" + campus + ", edificio=" + edificio + ", fila="
				+ fila + ", columna=" + columna + ", numAsientosRotos=" + numAsientosRotos + ", planta=" + planta
				+ ", tipo_clase=" + tipo_clase + ", estructura=" + estructura + "]";
	}
	
}
