package com.classes.projet;

public class Livreur {
	private int id;
	private String nom;
	
	public Livreur(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
