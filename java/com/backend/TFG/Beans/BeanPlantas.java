package com.backend.TFG.Beans;

import java.util.List;

public class BeanPlantas {
	
	private Integer key;
	private List<String> listaClases;
	
	public BeanPlantas(Integer key, List<String> listaClases) {
		this.key = key;
		this.listaClases = listaClases;
	}
	
	
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public List<String> getListaClases() {
		return listaClases;
	}
	public void setListaClases(List<String> listaClases) {
		this.listaClases = listaClases;
	} 

}
