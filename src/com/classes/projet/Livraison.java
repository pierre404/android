package com.classes.projet;

public class Livraison {
	private int id;
	private Expediteur expediteur;
	private Colis colis;
	private Destinataire destinataire;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Livraison(int id, Expediteur expediteur, Colis colis,
			Destinataire destinataire) {
		super();
		this.id = id;
		this.expediteur = expediteur;
		this.colis = colis;
		this.destinataire = destinataire;
	}
	public Expediteur getExpediteur() {
		return expediteur;
	}
	public void setExpediteur(Expediteur expediteur) {
		this.expediteur = expediteur;
	}
	
	public Colis getColis() {
		return colis;
	}
	public void setColis(Colis colis) {
		this.colis = colis;
	}
	
	public Destinataire getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(Destinataire destinataire) {
		this.destinataire = destinataire;
	}
}
