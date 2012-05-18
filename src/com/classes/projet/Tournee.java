package com.classes.projet;

import java.util.List;


public class Tournee implements java.io.Serializable {
	
	public Tournee(Livreur livreur, String date_tournee,
			List<Livraison> livraison) {
		super();
		this.livreur = livreur;
		this.date_tournee = date_tournee;
		this.livraison = livraison;
	}
	
	public Tournee() {
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Livreur livreur;
	private String date_tournee;
	private List<Livraison> livraison;
	private int nombre_livraison;
	

	
	public Livreur getlivreur() {
		return livreur;
	}
	
	public void setlivreur(Livreur lelivreur) {
		this.livreur = lelivreur;
	}
	
	public int getnbr() {
		return nombre_livraison;
	}
	
	public void setnbr(int nombre) {
		this.nombre_livraison = nombre;
	}


	public String getDate_tournee() {
		return date_tournee;
	}

	public void setDate_tournee(String date_tournee) {
		this.date_tournee = date_tournee;
	}

	public List<Livraison> getLivraison() {
		return livraison;
	}

	public void setLivraison(List<Livraison> livraison) {
		this.livraison = livraison;
	} 
}
