package com.exia.android;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.classes.projet.Livraison;
import com.classes.projet.Tournee;

public class ShowLivraison extends Activity {
	
	private TextView detailsDest;
	private TextView detailsExp;
	private TextView nbrColis;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_livraison);
		
		Bundle bundy = this.getIntent().getExtras();
		Tournee t = (Tournee) bundy.get("Tournee");
		
		Livraison currentLivraison = t.getListeLivraison().get(getCurrentLivraison(t.getListeLivraison()));
		
		detailsDest = (TextView) findViewById(R.id.details_dest);		
		detailsDest.setText("Nom Prénom : " + currentLivraison.getDestinataire().getNom() + "\n"
				+ "Adresse : "  + currentLivraison.getDestinataire().getRue() + "\n"
				+ "Complément Adresse : "  + currentLivraison.getDestinataire().getComplement_adresse() + "\n"
				+ "Code Postal : "  + currentLivraison.getDestinataire().getCp() + "\n"
				+ "Ville : "  + currentLivraison.getDestinataire().getVille() + "\n"
				+ "Téléphone : "  + currentLivraison.getDestinataire().getTelephone() + "\n"
				+ "Portable : "  + currentLivraison.getDestinataire().getPortable() + "\n");
		
		detailsExp = (TextView) findViewById(R.id.details_exp);
		detailsExp.setText("Nom Prénom : " + currentLivraison.getExpediteur().getNom() + "\n"
				+ "Adresse : "  + currentLivraison.getExpediteur().getRue() + "\n"
				+ "Code Postal : "  + currentLivraison.getExpediteur().getCp() + "\n"
				+ "Ville : "  + currentLivraison.getExpediteur().getVille() + "\n"
				+ "Téléphone : "  + currentLivraison.getExpediteur().getTelephone() + "\n");
		nbrColis.setText(currentLivraison.getNbr_colis());
		
	}
	
	public int getCurrentLivraison(ArrayList<Livraison> livraisons)
	{
		int i = livraisons.size() - 1;
		while (livraisons.get(i).getStatuts() == 0 && i >= 0)
		{
			i--;
		}
		return i;
	}

}
