package com.backend.TFG.Beans;

import java.util.List;


public class BeanValor {

	private String key;
	private List<BeanPlantas> listaPlantas;
	
	
	public BeanValor(String key, List<BeanPlantas> listaPlantas) {
		this.key = key;
		this.listaPlantas = listaPlantas;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<BeanPlantas> getListaPlantas() {
		return listaPlantas;
	}
	public void setListaPlantas(List<BeanPlantas> listaPlantas) {
		this.listaPlantas = listaPlantas;
	}
	
}
