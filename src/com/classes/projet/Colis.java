package com.classes.projet;

import java.util.List;

public class Colis {
	
	public Colis(int nombre, List<Paquet> une_liste_de_paquet) {
		super();
		this.nombre = nombre;
		this.listePaquet = une_liste_de_paquet;
	}
	public Colis() {
	}
	
	private int nombre;
	private List<Paquet> listePaquet;
	
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	public List<Paquet> getListePaquet() {
		return listePaquet;
	}
	public void setListePaquet(List<Paquet> paquet) {
		this.listePaquet = paquet;
	}
	public void setPaquet(Paquet paquet)
	{
		this.listePaquet.add(paquet);
	}
	public Paquet getPaquet(int num)
	{
		return listePaquet.get(num);
	}
}
