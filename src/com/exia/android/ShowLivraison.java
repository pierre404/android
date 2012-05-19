package com.exia.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.classes.projet.Livraison;
import com.classes.projet.Tournee;

public class ShowLivraison extends Activity {
	
	private TextView detailsDest;
	private TextView detailsExp;
	private TextView nbrColis;
	private Button navigation;
	private Livraison currentLivraison = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_livraison);
		
		Tournee t = Tournee.getInstance();
		
		Log.i("merde", t.getListeLivraison().size() + " ");
		
		currentLivraison = t.getListeLivraison().get(getCurrentLivraison(t.getListeLivraison()));
		
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
		//nbrColis.setText(currentLivraison.getNbr_colis());
		
		navigation = (Button)findViewById(R.id.launch_navigation);
		navigation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {				
				String url = "google.navigation:q=" + currentLivraison.getDestinataire().getCoordGPS().getLatitude() + "," + currentLivraison.getDestinataire().getCoordGPS().getLongitude();
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(i);
			}
		});
		
	}
	
	public int getCurrentLivraison(ArrayList<Livraison> livraisons)
	{
		int i = livraisons.size() - 1;
		if(i > -1)
		{			
			while (i >= 1 && livraisons.get(i).getStatuts() == 0)
			{
				i--;
			}
		}
		return i;
	}
	
	@Override
	public void onBackPressed()         
	{
		super.onBackPressed();
		finish();          
    }
}
