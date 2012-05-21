package com.exia.android;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.classes.projet.CoordGPS;
import com.classes.projet.Expediteur;
import com.classes.projet.Livraison;
import com.classes.projet.Tournee;
import com.exia.algoant.AntExecution;

/**
 * 
 * Classe permettant d'ajouté une adresse de destination où récupéré un colis
 * 
 * @author Benoit
 * 
 */
public class AddDest extends Activity {

	private EditText nom;
	private EditText rue;
	private EditText cp;
	private EditText ville;
	private EditText telephone;

	private Button cancel;
	private Button validate;

	private ProgressDialog chargement;
	private Handler handler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_dest);

		// Bouton valider et annuler
		cancel = (Button) findViewById(R.id.cancel);
		validate = (Button) findViewById(R.id.validate);

		// Les différents champs de saisie
		nom = (EditText) findViewById(R.id.name);
		rue = (EditText) findViewById(R.id.rue);
		cp = (EditText) findViewById(R.id.cp);
		ville = (EditText) findViewById(R.id.ville);
		telephone = (EditText) findViewById(R.id.telephone);

		// Action sur le bouton annuler
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		// Action sur le bouton valider
		validate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Message affichant le chargement
				chargement = new ProgressDialog(AddDest.this);
				chargement.setMessage("Chargement ...");
				handler = new Handler() {
					public void handleMessage(Message msg) {
						switch (msg.what) {
						case 0:
							chargement.dismiss();
							break;
						case 1:
							AlertDialog.Builder adb = new AlertDialog.Builder(
									AddDest.this);
							adb.setTitle("Adresse Invalide");
							adb.setMessage("Impossible de trouver l'adresse indiquée");
							adb.setPositiveButton("Fermer", null);
							adb.show();
							chargement.dismiss();
							break;
						}
					}
				};
				chargement.setCancelable(false);
				chargement.setCanceledOnTouchOutside(false);
				chargement.show();
				// Thread éxécutant la fonction calculant l'ordre des livraisons
				new Thread() {
					public void run() {
						// Récupération des données de l'expéditeur,
						// récupération des coordonnées et ajout à la liste
						Expediteur e = new Expediteur(nom.getText().toString(),
								rue.getText().toString(), cp.getText()
										.toString(),
								ville.getText().toString(), telephone.getText()
										.toString());
						e.setCoordGPS(new CoordGPS(e.getRue() + " " + e.getCp()
								+ " " + e.getVille(), AddDest.this));
						if (e.getCoordGPS().getLatitude() != 0
								|| e.getCoordGPS().getLatitude() != 0) {
							Tournee.getInstance().getListeLivraison()
									.add(new Livraison("", e, null, 0, 0, ""));
							Tournee.getInstance().setNbr(
									Tournee.getInstance().getNbr() + 1);

							ArrayList<Livraison> remainingLivraison = new ArrayList<Livraison>();
							ArrayList<Livraison> doneLivraison = new ArrayList<Livraison>();

							for (int i = 0; i < Tournee.getInstance()
									.getListeLivraison().size(); i++) {
								if (Tournee.getInstance().getListeLivraison()
										.get(i).getStatuts() != 0) {
									doneLivraison.add(Tournee.getInstance()
											.getListeLivraison().get(i));
								} else {
									remainingLivraison.add(Tournee
											.getInstance().getListeLivraison()
											.get(i));
								}
							}

							// Préparation de l'algorithme de colonie de fourmie
							AntExecution ae = new AntExecution(
									remainingLivraison,
									false,
									new CoordGPS(
											Utils.getLocationGPS(AddDest.this)[0],
											Utils.getLocationGPS(AddDest.this)[1]));

							ArrayList<Livraison> newOrderLivr = new ArrayList<Livraison>();
							newOrderLivr.addAll(doneLivraison);

							// Puis on lance le tout
							newOrderLivr.addAll(ae.run());

							Tournee.getInstance().setListeLivraison(
									newOrderLivr);
							handler.sendEmptyMessage(0);
							finish();
						} else {
							handler.sendEmptyMessage(1);
						}
					};
				}.start();
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
