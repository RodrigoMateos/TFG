package com.backend.TFG.Beans;

import java.util.List;
import ClasesAux.Tipos;

public class BeanAula {
	private String edificio, fila, numAsientosRotos, planta, tipoAula;
	private int estructura, numColumnas;
	private List<BeanRotos> listaRotos;
	private Integer[] listaFilas;
	
	public String getEdificio() {
		return edificio;
	}
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	public String getFila() {
		return fila;
	}
	public void setFila(String fila) {
		this.fila = fila;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public Integer[] getListaFilas() {
		return listaFilas;
	}
	public void setListaFilas(Integer[] listaFilas) {
		this.listaFilas = listaFilas;
	}
	public String getNumAsientosRotos() {
		return numAsientosRotos;
	}
	public void setNumAsientosRotos(String numAsientosRotos) {
		this.numAsientosRotos = numAsientosRotos;
	}
	public List<BeanRotos> getListaRotos() {
		return listaRotos;
	}
	public void setListaRotos(List<BeanRotos> listaRotos) {
		this.listaRotos = listaRotos;
	}
	public Long getEstructura() {
		return Long.valueOf(estructura);
	}
	public void setEstructura(int estructura) {
		this.estructura = estructura;
	}

	
	
	public Integer[] rellenarArray(Integer[] array) {
		return new Integer[5];
	}
	
	public Tipos buscarTipo(Long id) {
		if(id==1)
			return Tipos.NORMAL;
		else
			return Tipos.ESPECIAL;
	}
	public int getNumColumnas() {
		return numColumnas;
	}
	public void setNumColumnas(int numColumnas) {
		this.numColumnas = numColumnas;
	}
	public String getTipoAula() {
		return tipoAula;
	}
	public void setTipoAula(String tipoAula) {
		this.tipoAula = tipoAula;
	}
}
