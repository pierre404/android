package com.exia.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.classes.projet.Livraison;
import com.classes.projet.Tournee;
import com.exia.constants.Status;

public class DetailsDelivery extends Activity{
	
	private TextView detailsExp;
	private int indexDelivery;
	private Livraison currentLivraison;
	
	private Button acceptButton;
	private Button denyButton;
	private Button absentButton;
	
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
	
	private void setActionButton()
	{
		acceptButton = (Button)findViewById(R.id.accept_parcel);
		denyButton = (Button)findViewById(R.id.deny_parcel);
		absentButton = (Button)findViewById(R.id.absent);
		
		acceptButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				currentLivraison.setStatus(Status.ACCEPT);
				startActivityForResult(new Intent(DetailsDelivery.this, Sign.class), 1000);
			}
		});
		
		denyButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				currentLivraison.setStatus(Status.DENY);
				startActivityForResult(new Intent(DetailsDelivery.this, Sign.class), 1000);
			}
		});
		
		absentButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				currentLivraison.setStatus(Status.ABSENT);
				finish();
			}
		});
	}
	
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
	
	@Override
	public void onBackPressed()         
	{
		super.onBackPressed();
		finish();          
    }
}
