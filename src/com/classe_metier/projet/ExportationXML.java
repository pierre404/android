package com.classe_metier.projet;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.classes.projet.Colis;
import com.classes.projet.CoordGPS;
import com.classes.projet.Livraison;
import com.classes.projet.Livreur;
import com.classes.projet.Tournee;

/**
 * Classe permettant l'exporation XML des données
 * 
 * @author Benoit
 * 
 */
public class ExportationXML {

	/* Constructeur */
	/**
	 * Constructeur
	 * 
	 */
	public ExportationXML() {

	}

	/* Methodes */

	/**
	 * Fonction permettant la création du fichier
	 * 
	 * @param nom_fichier
	 *            Le nom du fichier
	 */
	public void creation_fichier_XML(String nom_fichier) {

		String ENCODING = "UTF-8";
		String en_tete = "<?xml version=\"1.0\" encoding=\"" + ENCODING
				+ "\"?>\n";

		try {
			new PrintWriter(new FileOutputStream(nom_fichier));
			ecriture_dans_XML(nom_fichier, en_tete);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Ecrit dans le fichier XML
	 * 
	 * @param nom_fichier
	 *            Le nom du fichier
	 * @param chaine
	 *            La chaine a écrire
	 */
	public void ecriture_dans_XML(String nom_fichier, String chaine) {
		String nomFic = nom_fichier;
		// on va chercher le chemin et le nom du fichier et on me tout ca dans
		// un String
		String adressedufichier = System.getProperty("user.dir") + "/" + nomFic;

		// on met try si jamais il y a une exception
		try {
			/**
			 * BufferedWriter a besoin d un FileWriter, les 2 vont ensemble, on
			 * donne comme argument le nom du fichier true signifie qu on ajoute
			 * dans le fichier (append), on ne marque pas par dessus
			 */
			FileWriter fw = new FileWriter(adressedufichier, true);

			// le BufferedWriter output auquel on donne comme argument le
			// FileWriter fw cree juste au dessus
			BufferedWriter output = new BufferedWriter(fw);

			// on marque dans le fichier ou plutot dans le BufferedWriter qui
			// sert comme un tampon(stream)
			output.write(chaine);

			// on peut utiliser plusieurs fois methode write
			output.flush();
			// ensuite flush envoie dans le fichier, ne pas oublier cette
			// methode pour le BufferedWriter

			output.close();
			// et on le ferme
			System.out.println("fichier créé");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}
	}

	/**
	 * Permet l'exportation de la tournéé
	 * 
	 * @param la_tournee
	 *            La tournee a exporter
	 */
	public void exportation(Tournee la_tournee) {
		String date_tournee = la_tournee.getDate_tournee().replaceAll("/", "-");
		String nom_fichier = "/sdcard/tournee_" + date_tournee + ".xml";
		int nombre_livraison = 0;
		// ArrayList<Livraison> liste_livraison=la_tournee.getListeLivraison();
		Livreur livreur_tournee = la_tournee.getlivreur();

		creation_fichier_XML(nom_fichier);

		ecriture_dans_XML(nom_fichier, "<tournee>\n");
		ecriture_dans_XML(nom_fichier, "	<livreur>\n");
		ecriture_dans_XML(nom_fichier, "		<id>" + livreur_tournee.getId()
				+ "</id>\n");
		ecriture_dans_XML(nom_fichier, "		<nom>" + livreur_tournee.getNom()
				+ "</nom>\n");
		ecriture_dans_XML(nom_fichier, "	</livreur>\n");
		ecriture_dans_XML(nom_fichier,
				"	<date_tournee>" + la_tournee.getDate_tournee()
						+ "</date_tournee>\n");
		ecriture_dans_XML(nom_fichier,
				"	<nombre_livraison>" + la_tournee.getNbr()
						+ "</nombre_livraison>\n");

		while (la_tournee.getNbr() > nombre_livraison) {
			Livraison une_livraison = new Livraison();
			int nbr_colis = 0;

			une_livraison = la_tournee.getLivraison(nombre_livraison);

			nombre_livraison++;

			ecriture_dans_XML(nom_fichier, "	<livraison>\n");

			CoordGPS gps = une_livraison.getCoordGPS();
			/*
			 * CoordGPS gps = new CoordGPS();
			 * 
			 * gps=une_livraison.getCoordGPS();
			 */

			ecriture_dans_XML(nom_fichier, "		<id>" + une_livraison.getId()
					+ "</id>\n");
			ecriture_dans_XML(nom_fichier, "		<date>" + une_livraison.getDate()
					+ "</date>\n");
			ecriture_dans_XML(nom_fichier,
					"		<status>" + une_livraison.getStatuts() + "</status>\n");
			ecriture_dans_XML(nom_fichier,
					"		<commentaire>" + une_livraison.getCommentaire()
							+ "</commentaire>\n");
			ecriture_dans_XML(nom_fichier,
					"		<poids_total>" + une_livraison.getPoids_total()
							+ "</poids_total>\n");
			ecriture_dans_XML(nom_fichier, "		<expediteur>\n");
			ecriture_dans_XML(nom_fichier, "			<nom>"
					+ une_livraison.getExpediteur().getNom() + "</nom>\n");
			ecriture_dans_XML(nom_fichier, "			<rue>"
					+ une_livraison.getExpediteur().getRue() + "</rue>\n");
			ecriture_dans_XML(nom_fichier, "			<cp>"
					+ une_livraison.getExpediteur().getCp() + "</cp>\n");
			ecriture_dans_XML(nom_fichier, "			<ville>"
					+ une_livraison.getExpediteur().getVille() + "</ville>\n");
			ecriture_dans_XML(nom_fichier, "			<telephone>"
					+ une_livraison.getExpediteur().getTelephone()
					+ "</telephone>\n");
			ecriture_dans_XML(nom_fichier, "		</expediteur>\n");
			if (une_livraison.getDestinataire() != null) {
				ecriture_dans_XML(nom_fichier, "		<destinataire>\n");
				ecriture_dans_XML(nom_fichier, "			<nom>"
						+ une_livraison.getDestinataire().getNom() + "</nom>\n");
				ecriture_dans_XML(nom_fichier, "			<rue>"
						+ une_livraison.getDestinataire().getRue() + "</rue>\n");
				ecriture_dans_XML(nom_fichier, "			<cp>"
						+ une_livraison.getDestinataire().getCp() + "</cp>\n");
				ecriture_dans_XML(nom_fichier, "			<ville>"
						+ une_livraison.getDestinataire().getVille()
						+ "</ville>\n");
				ecriture_dans_XML(nom_fichier, "			<complement_adresse>"
						+ une_livraison.getDestinataire()
								.getComplement_adresse()
						+ "</complement_adresse>\n");
				ecriture_dans_XML(nom_fichier, "			<telephone>"
						+ une_livraison.getDestinataire().getTelephone()
						+ "</telephone>\n");
				ecriture_dans_XML(nom_fichier, "			<portable>"
						+ une_livraison.getDestinataire().getPortable()
						+ "</portable>\n");
				ecriture_dans_XML(nom_fichier, "		</destinataire>\n");
			}
			if (une_livraison.getCoordGPS() != null) {
				ecriture_dans_XML(nom_fichier, "		<coordonnees_GPS>\n");
				ecriture_dans_XML(nom_fichier,
						"			<latitude>" + gps.getLatitude() + "</latitude>\n");
				ecriture_dans_XML(nom_fichier,
						"			<longitude>" + gps.getLongitude()
								+ "</longitude>\n");
				ecriture_dans_XML(nom_fichier, "		</coordonnees_GPS>\n");
			}
			ecriture_dans_XML(nom_fichier, "		<colis>\n");
			ecriture_dans_XML(nom_fichier,
					"			<nbr_colis>" + une_livraison.getNbr_colis()
							+ "</nbr_colis>\n");

			while (une_livraison.getNbr_colis() > nbr_colis) {
				Colis un_colis = new Colis();
				un_colis = une_livraison.getColis(nbr_colis);
				nbr_colis++;
				ecriture_dans_XML(nom_fichier, "			<paquet>\n");
				ecriture_dans_XML(nom_fichier,
						"				<code_barre>" + un_colis.getCode_barre()
								+ "</code_barre>\n");
				ecriture_dans_XML(nom_fichier,
						"				<taille>" + un_colis.getTaille() + "</taille>\n");
				ecriture_dans_XML(nom_fichier,
						"				<poids>" + un_colis.getPoids() + "</poids>\n");
				ecriture_dans_XML(nom_fichier, "			</paquet>\n");
			}
			ecriture_dans_XML(nom_fichier, "		</colis>\n");
			ecriture_dans_XML(nom_fichier, "	</livraison>\n");
		}

		ecriture_dans_XML(nom_fichier, "</tournee>\n");

	}
}
