package com.classes.projet;

public class Livreur {
	/*Attribut*/
	private int id;
	private String nom;
	
	/*Constructeur*/
	public Livreur(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	public Livreur() {
	}
	
	/*Getter & Setter*/
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
