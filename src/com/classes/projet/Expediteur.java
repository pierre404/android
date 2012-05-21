package com.classes.projet;

/**
 * 
 * Classe matérialisant un expéditeur
 * 
 * @author Benoit
 * 
 */
public class Expediteur {

	/* Constructeur */
	/**
	 * @param nom
	 *            Le nom
	 * @param rue
	 *            La rue
	 * @param cp
	 *            Le code postale
	 * @param ville
	 *            La ville
	 * @param telephone
	 *            Le téléphone
	 */
	public Expediteur(String nom, String rue, String cp, String ville,
			String telephone) {
		super();
		this.nom = nom;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
		this.telephone = telephone;
	}

	public Expediteur() {

	}

	/* Attribut */
	private String nom;
	private String rue;
	private String cp;
	private String ville;
	private String telephone;

	private CoordGPS coordGPS;

	/* Getter & Setter */
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public CoordGPS getCoordGPS() {
		return coordGPS;
	}

	public void setCoordGPS(CoordGPS coordGPS) {
		this.coordGPS = coordGPS;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
