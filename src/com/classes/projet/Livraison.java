package com.classes.projet;

import java.util.ArrayList;

public class Livraison {
	/*Constructeur */
	public Livraison(String id, Expediteur expediteur, Colis colis,
			Destinataire destinataire, int status, int motif_refus, String commentaire) {
		super();
		this.id = id;
		this.expediteur = expediteur;
		this.colis = colis;
		this.destinataire = destinataire;
		this.status=status;
		this.motif_refus=motif_refus;
		this.commentaire=commentaire;
	}
	
	public Livraison() {
	}
	
	/*Attribut*/
	private Expediteur expediteur;
	private Colis colis;
	private Destinataire destinataire;
	private int status,motif_refus;
	private String commentaire,id;
	private ArrayList<Colis> listeColis;
	
	/*Getter & Setter*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public int getStatuts()
	{
		return this.status;
	}
	
	public void setStatus(int le_status)
	{
		status=le_status;
	}
	
	public int getMotifRefus()
	{
		return this.motif_refus;
	}
	
	public void setMotifRefuss(int le_motif)
	{
		motif_refus=le_motif;
	}
	
	public String getCommentaire()
	{
		return this.commentaire;
	}
	
	public void setCommentaire(String le_commentaire)
	{
		commentaire=le_commentaire;
	}
	
	public ArrayList<Colis> getListeColis() {
		return listeColis;
	}
	public void setListePaquet(ArrayList<Colis> paquet) {
		this.listeColis = paquet;
	}
	public void setPaquet(Colis paquet)
	{
		this.listeColis.add(paquet);
	}
	public Colis getPaquet(int num)
	{
		return listeColis.get(num);
	}
}
