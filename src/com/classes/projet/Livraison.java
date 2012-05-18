package com.classes.projet;

import java.util.List;

public class Livraison {
	/*Constructeur */
	public Livraison(String id, Expediteur expediteur,Destinataire destinataire, int status, int motif_refus, String commentaire) {
		super();
		this.id = id;
		this.expediteur = expediteur;
		this.destinataire = destinataire;
		this.status=status;
		this.motif_refus=motif_refus;
		this.commentaire=commentaire;
	}
	
	public Livraison() {
	}
	
	/*Attribut*/
	private Expediteur expediteur;
	private Destinataire destinataire;
	private int status,motif_refus,nbr_Colis;
	private String commentaire,id;
	private List<Colis> listeColis;
	
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
	
	public List<Colis> getListeColis() {
		return listeColis;
	}
	public void setListePaquet(List<Colis> paquet) {
		this.listeColis = paquet;
	}
	public void setColis(Colis paquet)
	{
		this.listeColis.add(paquet);
	}
	public Colis getColis(int num)
	{
		return listeColis.get(num);
	}
	public void setNbr_colis(int nbr)
	{
		this.nbr_Colis=nbr;
	}
	public int getNbr_colis()
	{
		return nbr_Colis;
	}
}
