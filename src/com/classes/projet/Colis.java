package com.classes.projet;


public class Colis {
	
	/*Constructeur*/
	public Colis(String id,String la_taille,String le_poids) {
		super();
		this.code_barre = id;
		this.taille=la_taille;
		this.poids=le_poids;
	}
	public Colis() {
	}
	
	/*Attribut*/
	private String code_barre,taille,poids;
	
	/*Getter & Setter*/
	public String getCode_barre() {
		return code_barre;
	}
	public void setCode_barre(String code_barre) {
		this.code_barre = code_barre;
	}
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}
	public String getPoids() {
		return poids;
	}
	public void setPoids(String poids) {
		this.poids = poids;
	}	
}
