package com.exia.android;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.classes.projet.Livraison;
import com.classes.projet.Tournee;
import com.exia.constants.Status;

/**
 * Activité affichant les informations d'une livraison
 * 
 * @author Benoit
 *
 */
public class DetailsDelivery extends Activity{
	
	private TextView detailsExp;
	private TextView comments;
	private int indexDelivery;
	private Livraison currentLivraison;
	
	private Button acceptButton;
	private Button denyButton;
	private Button absentButton;
	
	private Spinner spin;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_delivery);
		setActionButton();
		indexDelivery = getIntent().getExtras().getInt("indexDelivery");
		currentLivraison = Tournee.getInstance().getLivraison(indexDelivery);
		detailsExp = (TextView)findViewById(R.id.details_exp);
		detailsExp.setText("Nom Prénom : " + currentLivraison.getDestinataire().getNom() + "\n"
				+ "Adresse : "  + currentLivraison.getDestinataire().getRue() + "\n"
				+ "Complément Adresse : "  + currentLivraison.getDestinataire().getComplement_adresse() + "\n"
				+ "Code Postal : "  + currentLivraison.getDestinataire().getCp() + "\n"
				+ "Ville : "  + currentLivraison.getDestinataire().getVille() + "\n"
				+ "Téléphone : "  + currentLivraison.getDestinataire().getTelephone() + "\n"
				+ "Portable : "  + currentLivraison.getDestinataire().getPortable() + "\n");		
	}
	
	
	/**
	 * Définit les actions des différents bouton de l'acitivité
	 */
	private void setActionButton()
	{
		acceptButton = (Button)findViewById(R.id.accept_parcel);
		denyButton = (Button)findViewById(R.id.deny_parcel);
		absentButton = (Button)findViewById(R.id.absent);
		comments = (TextView)findViewById(R.id.comments);
		
		acceptButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				currentLivraison.setStatus(Status.ACCEPT);
				currentLivraison.setCommentaire(comments.getText().toString());
				Intent i = new Intent(DetailsDelivery.this, Sign.class);
				i.putExtra("indexDelivery", indexDelivery);
				startActivityForResult(i, 1000);
			}
		});
		
		denyButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder adb = new AlertDialog.Builder(DetailsDelivery.this);
				spin = new Spinner(DetailsDelivery.this);
				ArrayList<String> denyReason = new ArrayList<String>();
				denyReason.add("Colis endomagé");
				denyReason.add("Le destinataire n'habite plus à cette adresse");
				denyReason.add("Colis indésirable");
				ArrayAdapter<String> aa = new ArrayAdapter<String>(DetailsDelivery.this, android.R.layout.simple_spinner_item, denyReason);
				aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spin.setAdapter(aa);
				adb.setTitle("Indiquer le motif du refus : ");
				adb.setView(spin);
				adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            	currentLivraison.setStatus(Status.DENY);
						currentLivraison.setCommentaire(comments.getText().toString());						
						currentLivraison.setMotifRefuss(spin.getSelectedItemPosition() + 1);
						Intent i = new Intent(DetailsDelivery.this, Sign.class);
						i.putExtra("indexDelivery", indexDelivery);
						startActivityForResult(i, 1000);
		            }
		        });
				adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		          } });
		        adb.show();				
			}
		});
		
		absentButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				currentLivraison.setStatus(Status.ABSENT);
				currentLivraison.setCommentaire(comments.getText().toString());
				DetailsDelivery.this.finish();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
	      // on récupère le statut de retour de l'activité 2 c'est à dire l'activité numéro 1000
	      if(requestCode==1000){
	         // si le code de retour est égal à 1 on stoppe l'activité 1
	         if(resultCode==1){
	            finish();
	         }
	      }
	      super.onActivityResult (requestCode, resultCode, data);
	   }
	
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
