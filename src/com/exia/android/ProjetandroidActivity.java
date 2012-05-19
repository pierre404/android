package com.exia.android;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.classes.projet.Colis;
import com.classes.projet.CoordGPS;
import com.classes.projet.Destinataire;
import com.classes.projet.Expediteur;
import com.classes.projet.Livraison;
import com.classes.projet.Tournee;
import com.exia.algoant.AntExecution;

/**
 * Activité principale de l'application
 * 
 * @author Benoit
 * 
 */
public class ProjetandroidActivity extends Activity {
	private Button leaveButton = null;
	private Button scanButton = null;
	private Button showListButton = null;
	private Tournee t;
	private ArrayList<Colis> colisscane = new ArrayList<Colis>();
	private Livraison currentLivraison;
	private ProgressDialog chargement;
	private Handler handler;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		leaveButton = (Button) findViewById(R.id.leave);
		scanButton = (Button) findViewById(R.id.scan_parcel);
		showListButton = (Button) findViewById(R.id.show_list);

		scanButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
				intent.putExtra("SCAN_WIDTH", 800);
				intent.putExtra("SCAN_HEIGHT", 200);
				intent.putExtra("PROMPT_MESSAGE", "Scan d'un colis");
				startActivityForResult(intent, 0);
			}
		});

		showListButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ProjetandroidActivity.this,
						ShowLivraison.class);
				startActivity(i);
			}
		});

		leaveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ProjetandroidActivity.this.finish();
			}
		});

		chargementDonnees();

		/*
		 * AlertDialog.Builder adb = new AlertDialog.Builder(this);
		 * adb.setTitle("Google Map"); adb.setMessage(coord[0] +
		 * "Impossible de trouver cette adresse !" + coord[1]);
		 * adb.setPositiveButton("Fermer", null); adb.show();
		 */
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				// String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				int indexCurrentLivraison = -1;
				Colis colisscan = null;
				Livraison currentLivraison = null;
				for (int i = 0; i < t.getListeLivraison().size(); i++) {
					for (int j = 0; j < t.getListeLivraison().get(i)
							.getListeColis().size(); j++) {
						if (t.getListeLivraison().get(i).getListeColis().get(j)
								.getCode_barre().equals(contents)) {
							colisscan = t.getListeLivraison().get(i)
									.getListeColis().get(j);
							currentLivraison = t.getListeLivraison().get(i);
							indexCurrentLivraison = i;
						}
					}
				}
				if (colisscan == null) {
					AlertDialog.Builder adb = new AlertDialog.Builder(this);
					adb.setTitle("Erreur de scan");
					adb.setMessage("Ce code barre ne correspond à aucun colis répertorié !");
					adb.setPositiveButton("Fermer", null);
					adb.show();

				} else {
					if (this.currentLivraison == null) {
						this.currentLivraison = currentLivraison;
					}
					if (!currentLivraison.equals(this.currentLivraison)) {
						AlertDialog.Builder adb = new AlertDialog.Builder(this);
						adb.setTitle("Erreur de scan");
						adb.setMessage("Ce code barre n'appartient pas à la livraison en cours !");
						adb.setPositiveButton("Fermer", null);
						adb.show();
					} else {
						if (this.colisscane.contains(colisscan)) {
							AlertDialog.Builder adb = new AlertDialog.Builder(
									this);
							adb.setTitle("Erreur de scan");
							adb.setMessage("Vous avez déjà scanné ce colis !");
							adb.setPositiveButton("Fermer", null);
							adb.show();

						} else {
							this.colisscane.add(colisscan);
							if (currentLivraison.getNbr_colis() == this.colisscane
									.size()) {
								this.currentLivraison = null;
								Intent i = new Intent(this,
										DetailsDelivery.class);
								i.putExtra("indexDelivery",
										indexCurrentLivraison);
								startActivity(i);

							} else {
								AlertDialog.Builder adb = new AlertDialog.Builder(
										this);
								int nbrecolis = currentLivraison.getNbr_colis()
										- this.colisscane.size();
								adb.setTitle("Scan validé");
								adb.setMessage("Il reste " + nbrecolis
										+ " colis à scanner !");
								adb.setPositiveButton("Fermer", null);
								adb.show();
							}
						}
					}
				}
				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		Utils.checkGPS(this);
	}

	/**
	 * Charge les données issu du fichier XML
	 * 
	 */
	private void chargementDonnees() {
		chargement = new ProgressDialog(this);
		chargement.setMessage("Chargement ...");
		handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					chargement.dismiss();
					break;
				}
			}
		};

		chargement.show();
		new Thread() {
			public void run() {
				Destinataire d1 = new Destinataire("Mouquet Benoit",
						"9, rue du beau soleil", "76660", "Londinières", "",
						"0640191362", "0640191362");
				d1.setCoordGPS(new CoordGPS(d1.getRue() + " " + d1.getCp()
						+ " " + d1.getVille(), ProjetandroidActivity.this));
				Livraison l1 = new Livraison("505465654", new Expediteur(
						"Exia.cesi", "1 rue G Marconi", "76130",
						"Mont Saint Aignan", "0235214256"), d1, 0, 0, "");
				Destinataire d2 = new Destinataire("Beuvin Juliette",
						"1, rue victor Boucher", "76270", "Neufchatel en Bray",
						"", "0640191362", "0640191362");
				d2.setCoordGPS(new CoordGPS(d2.getRue() + " " + d2.getCp()
						+ " " + d2.getVille(), ProjetandroidActivity.this));
				Livraison l2 = new Livraison("505465654", new Expediteur(
						"Exia.cesi", "1 rue G Marconi", "76130",
						"Mont Saint Aignan", "0235214256"), d2, 0, 0, "");
				Destinataire d3 = new Destinataire("Ridel Nicolas",
						"76 rue de l'église", "76190", "Auzebosc", "",
						"0640191362", "0640191362");
				d3.setCoordGPS(new CoordGPS(d3.getRue() + " " + d3.getCp()
						+ " " + d3.getVille(), ProjetandroidActivity.this));
				Livraison l3 = new Livraison("505465654", new Expediteur(
						"Exia.cesi", "1 rue G Marconi", "76130",
						"Mont Saint Aignan", "0235214256"), d3, 0, 0, "");
				Destinataire d4 = new Destinataire("Pierre Ruggirello", "",
						"27200", "Vernon", "", "0640191362", "0640191362");
				d4.setCoordGPS(new CoordGPS(d4.getRue() + " " + d4.getCp()
						+ " " + d4.getVille(), ProjetandroidActivity.this));
				Livraison l4 = new Livraison("505465654", new Expediteur(
						"Exia.cesi", "1 rue G Marconi", "76130",
						"Mont Saint Aignan", "0235214256"), d4, 0, 0, "");
				ArrayList<Livraison> list = new ArrayList<Livraison>();
				l1.setColis(new Colis("3274080005003", "30*30*30", "400g"));
				// l1.setColis(new Colis("3103220009710", "30*30*30", "400g"));
				l1.setNbr_colis(1);
				list.add(l1);
				list.add(l2);
				list.add(l3);
				list.add(l4);
				t = Tournee.getInstance();

				t.setListeLivraison(list);

				AntExecution ae = new AntExecution(t.getListeLivraison());

				t.setListeLivraison(ae.run());
				handler.sendEmptyMessage(0);
			};
		}.start();
	}
}