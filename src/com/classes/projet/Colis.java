package com.classes.projet;

/**
 * 
 * Classe matérialisant un colis
 * 
 * @author Benoit
 * 
 */
public class Colis {

	/* Constructeur */
	/**
	 * @param id
	 *            L'id du colis
	 * @param la_taille
	 *            Sa taille
	 * @param le_poids
	 *            Son poids
	 */
	public Colis(String id, String la_taille, String le_poids) {
		super();
		this.code_barre = id;
		this.taille = la_taille;
		this.poids = le_poids;
	}
	
	public Colis() {
	}

	/* Attribut */
	private String code_barre, taille, poids;

	/* Getter & Setter */
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
