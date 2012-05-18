package com.classes.projet;

public class Destinataire {
	
	public Destinataire(String nom, String rue, String cp, String ville,
			String complement_adresse, String telephone, String portable) {
		super();
		this.nom = nom;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
		this.complement_adresse = complement_adresse;
		this.telephone = telephone;
		this.portable = portable;
	}
	public Destinataire() {
		
	}
	private String nom;
	private String rue;
	private String cp;
	private String ville;
	private String complement_adresse;
	private String telephone;
	private String portable;
	private int latitude,longitude;
	
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public String getComplement_adresse() {
		return complement_adresse;
	}
	public void setComplement_adresse(String complement_adresse) {
		this.complement_adresse = complement_adresse;
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
	
	public String getPortable() {
		return portable;
	}
	public void setPortable(String portable) {
		this.portable = portable;
	}
	
	public int getLatitude()
	{
		return latitude;
	}
	
	public void setLatitude(int la_latitude)
	{
		this.latitude=la_latitude;
	}
	public int getLongitude()
	{
		return longitude;
	}
	
	public void setLongitude(int la_longitude)
	{
		this.longitude=la_longitude;
	}
	
}
