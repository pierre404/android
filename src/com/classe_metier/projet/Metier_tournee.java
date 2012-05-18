package com.classe_metier.projet;


import com.classes.projet.Colis;
import com.classes.projet.Livraison;
import com.classes.projet.Livreur;
import com.classes.projet.Tournee;

public class Metier_tournee 
{	
	Tournee la_tournee = new Tournee();
	GestionXML xml = new GestionXML();
	
	public void ajout_livreur()
	{
		Livreur le_livreur=new Livreur();
		le_livreur.setId(xml.recup_id("/sdcard/tournee.xml","//livreur/id/"));
		le_livreur.setNom(xml.recup_donnee("/sdcard/tournee.xml", "//livreur/nom/"));
		la_tournee.setlivreur(le_livreur);
	}
	
	public void ajout_nbr_livraison()
	{
		la_tournee.setNbr(xml.nbr_livraison("/sdcard/tournee.xml"));
	}

	public void ajout_livraison()
	{
		int nbr_livraison=0;
		while(la_tournee.getNbr()>nbr_livraison)
		{
			nbr_livraison++;
			Livraison une_livraison=new Livraison();
			une_livraison.setNbr_colis(xml.recup_id("/sdcard/tournee.xml","//livraison["+nbr_livraison+"]/nombre/"));
			int nbr_colis=0;
			while(une_livraison.getNbr_colis()>nbr_colis)
			{
				nbr_colis++;
				Colis un_colis = new Colis();
				un_colis.setCode_barre(xml.recup_donnee("/sdcard/tournee.xml", "//livraison["+nbr_livraison+"]/colis/paquet["+nbr_colis+"]/code_barre/"));
				un_colis.setTaille(xml.recup_donnee("/sdcard/tournee.xml", "//livraison["+nbr_livraison+"]/colis/paquet["+nbr_colis+"]/taille/"));
				un_colis.setPoids(xml.recup_donnee("/sdcard/tournee.xml", "//livraison["+nbr_livraison+"]/colis/paquet["+nbr_colis+"]/poids/"));
				une_livraison.setColis(un_colis);
			}
			
		}
	}
	
	public void ajout_date_tournee()
	{
		la_tournee.setDate_tournee(xml.recup_donnee("/sdcard/tournee.xml", "//date"));
	}
}
