package com.classes.projet;

import java.util.List;

public class Colis {
	
	public Colis(int nombre, List<Paquet> paquet) {
		super();
		this.nombre = nombre;
		this.paquet = paquet;
	}
	private int nombre;
	private List<Paquet> paquet;
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	public List<Paquet> getPaquet() {
		return paquet;
	}
	public void setPaquet(List<Paquet> paquet) {
		this.paquet = paquet;
	}
	

}
