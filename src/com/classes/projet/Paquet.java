package com.classes.projet;

public class Paquet {
	
	public Paquet(String code_barre, String taille, String poids) {
		super();
		this.code_barre = code_barre;
		this.taille = taille;
		this.poids = poids;
	}
	private String code_barre;
	private String taille;
	private String poids;
	
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
