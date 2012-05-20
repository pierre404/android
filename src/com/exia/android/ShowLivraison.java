package com.exia.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.classes.projet.Livraison;
import com.classes.projet.Tournee;

/**
 * Affichage des informations d'une livraison
 * 
 * @author Benoit
 *
 */
public class ShowLivraison extends Activity {
	
	private TextView detailsDest;
	private TextView detailsExp;
	//private TextView nbrColis;
	private Button navigation;
	private Livraison currentLivraison = null;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_livraison);
		
		//On r�cup�re la tourn�e
		Tournee t = Tournee.getInstance();
		
		//Ainsi que la livraison en cours
		currentLivraison = t.getListeLivraison().get(this.getIntent().getExtras().getInt("indexDelivery"));
		
		//Si le destinataire n'est pas null, on affiche ses informations
		if(currentLivraison.getDestinataire() != null)
		{
			detailsDest = (TextView) findViewById(R.id.details_dest);		
			detailsDest.setText("Nom Pr�nom : " + currentLivraison.getDestinataire().getNom() + "\n"
					+ "Adresse : "  + currentLivraison.getDestinataire().getRue() + "\n"
					+ "Compl�ment Adresse : "  + currentLivraison.getDestinataire().getComplement_adresse() + "\n"
					+ "Code Postal : "  + currentLivraison.getDestinataire().getCp() + "\n"
					+ "Ville : "  + currentLivraison.getDestinataire().getVille() + "\n"
					+ "T�l�phone : "  + currentLivraison.getDestinataire().getTelephone() + "\n"
					+ "Portable : "  + currentLivraison.getDestinataire().getPortable() + "\n");
		}
		
		//Affichage des informations de l'exp�diteur
		detailsExp = (TextView) findViewById(R.id.details_exp);
		detailsExp.setText("Nom Pr�nom : " + currentLivraison.getExpediteur().getNom() + "\n"
				+ "Adresse : "  + currentLivraison.getExpediteur().getRue() + "\n"
				+ "Code Postal : "  + currentLivraison.getExpediteur().getCp() + "\n"
				+ "Ville : "  + currentLivraison.getExpediteur().getVille() + "\n"
				+ "T�l�phone : "  + currentLivraison.getExpediteur().getTelephone() + "\n");
		//nbrColis.setText(currentLivraison.getNbr_colis());
		
		//On affecte l'action au bouton pour lancer la navigation
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
	
	/*public int getCurrentLivraison(ArrayList<Livraison> livraisons)
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
	}*/
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed()         
	{
		super.onBackPressed();
		finish();          
    }
}
