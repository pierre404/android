package com.classes.projet;

import java.util.ArrayList;

/**
 * 
 * 
 * Classe matérialisant une tournee
 * 
 * @author Benoit
 * 
 */
public class Tournee implements java.io.Serializable {

	private static volatile Tournee instance = null;

	/* Constructeur */
	/**
	 * 
	 * Permet de récupérer l'unique instance de la classe tournée
	 * 
	 * @return
	 * 
	 *         L'objet tournée
	 */
	public final static Tournee getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
		// d'éviter un appel coûteux à synchronized,
		// une fois que l'instanciation est faite.
		if (Tournee.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized (Tournee.class) {
				if (Tournee.instance == null) {
					Tournee.instance = new Tournee();
				}
			}
		}
		return Tournee.instance;
	}

	private Tournee() {
		listeLivraison = new ArrayList<Livraison>();
	}

	/* Attribut */
	private static final long serialVersionUID = 1L;
	private Livreur livreur;
	private String date_tournee;
	private ArrayList<Livraison> listeLivraison;
	private int nombre_livraison;

	/* Getter & Setter */
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

	public ArrayList<Livraison> getListeLivraison() {
		return listeLivraison;
	}

	public void setListeLivraison(ArrayList<Livraison> livraison) {
		this.listeLivraison = livraison;
	}

	public Livraison getLivraison(int num) {
		return listeLivraison.get(num);
	}

	public void setLivraison(Livraison une_livraison) {
		this.listeLivraison.add(une_livraison);
	}
}
