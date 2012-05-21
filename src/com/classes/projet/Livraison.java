package com.classes.projet;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * Classe matérialisant une livraison
 * 
 * @author Benoit
 * 
 */
public class Livraison {
	/* Constructeur */
	/**
	 * @param id
	 *            L'id de la livraison
	 * @param expediteur
	 *            L'expéditeur
	 * @param destinataire
	 *            Le destinataire
	 * @param status
	 *            Le status
	 * @param motif_refus
	 *            Le motif de refus (si il y a lieu)
	 * @param commentaire
	 *            Le commentaire de la livraison
	 */
	public Livraison(String id, Expediteur expediteur,
			Destinataire destinataire, int status, int motif_refus,
			String commentaire) {
		super();
		this.id = id;
		this.expediteur = expediteur;
		this.destinataire = destinataire;
		this.commentaire = commentaire;
		this.listeColis = new ArrayList<Colis>();
		this.status = status;
		this.motif_refus = motif_refus;
	}

	public Livraison() {
	}

	/* Attribut */
	private Expediteur expediteur;
	private Destinataire destinataire;
	private int status, motif_refus, nombre_Colis;
	private String commentaire, id;
	private ArrayList<Colis> listeColis = new ArrayList<Colis>();
	private Date date_livraison;
	private CoordGPS coordGPS;
	private double poids_total;

	/* Getter & Setter */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPoids_total() {
		return poids_total;
	}

	public void setPoids_total(double le_poids) {
		this.poids_total = le_poids;
	}

	public Date getDate() {
		return date_livraison;
	}

	public void setDate(Date une_date) {
		this.date_livraison = une_date;
	}

	public CoordGPS getCoordGPS() {
		return coordGPS;
	}

	public void setCoordGPS(CoordGPS coordGPS) {
		this.coordGPS = coordGPS;
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

	public int getStatuts() {
		return this.status;
	}

	public void setStatus(int le_status) {
		status = le_status;
	}

	public int getMotifRefus() {
		return this.motif_refus;
	}

	public void setMotifRefuss(int le_motif) {
		motif_refus = le_motif;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String le_commentaire) {
		commentaire = le_commentaire;
	}

	public ArrayList<Colis> getListeColis() {
		return listeColis;
	}

	public void setListePaquet(ArrayList<Colis> paquet) {
		this.listeColis = paquet;
	}

	public void setColis(Colis paquet) {
		this.listeColis.add(paquet);
	}

	public Colis getColis(int num) {
		return listeColis.get(num);
	}

	public void setNbr_colis(int nbr) {
		this.nombre_Colis = nbr;
	}

	public int getNbr_colis() {
		return nombre_Colis;
	}
}
