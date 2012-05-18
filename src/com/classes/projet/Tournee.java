package com.classes.projet;

import java.util.List;


public class Tournee implements java.io.Serializable {
	
	/*Constructeur*/
	public Tournee(Livreur livreur, String date_tournee,
			List<Livraison> liste_livraison) {
		super();
		this.livreur = livreur;
		this.date_tournee = date_tournee;
		this.listeLivraison = liste_livraison;
	}
	
	public Tournee() {
		
	}

	/*Attribut*/
	private static final long serialVersionUID = 1L;
	private Livreur livreur;
	private String date_tournee;
	private List<Livraison> listeLivraison;
	private int nombre_livraison;
	

	/*Getter & Setter*/
	public Livreur getlivreur() {
		return livreur;
	}
	
	public void setlivreur(Livreur lelivreur) {
		this.livreur = lelivreur;
	}
	
	public int getNbr() {
		return nombre_livraison;
	}
	
	public void setNbr(int nombre) {
		this.nombre_livraison = nombre;
	}


	public String getDate_tournee() {
		return date_tournee;
	}

	public void setDate_tournee(String date_tournee) {
		this.date_tournee = date_tournee;
	}

	public List<Livraison> getListeLivraison() {
		return listeLivraison;
	}

	public void setListeLivraison(List<Livraison> livraison) {
		this.listeLivraison = livraison;
	}
	
	public Livraison getLivraison(int num)
	{
		return listeLivraison.get(num);
	}
	public void setLivraison(Livraison une_livraison)
	{
		this.listeLivraison.add(une_livraison);
	}
}
