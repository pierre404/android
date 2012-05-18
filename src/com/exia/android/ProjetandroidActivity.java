package com.exia.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.classes.projet.CoordGPS;
import com.classes.projet.Destinataire;
import com.classes.projet.Livraison;
import com.classes.projet.Tournee;
import com.exia.algoant.AntExecution;

public class ProjetandroidActivity extends Activity {
	private Button leaveButton = null;
	private Button scanButton = null;
	private Button showListButton = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Destinataire d1 = new Destinataire("Mouquet Benoit",
				"9, rue du beau soleil", "76660", "Londinières", "",
				"0640191362", "0640191362");
		d1.setCoordGPS(new CoordGPS(d1.getRue() + " " + d1.getCp() + " "
				+ d1.getVille(), this));
		Livraison l1 = new Livraison("505465654", null, d1, 0, 0, "");
		Destinataire d2 = new Destinataire("Beuvin Juliette",
				"1, rue victor Boucher", "76270", "Neufchatel en Bray", "",
				"0640191362", "0640191362");
		d2.setCoordGPS(new CoordGPS(d2.getRue() + " " + d2.getCp() + " "
				+ d2.getVille(), this));
		Livraison l2 = new Livraison("505465654", null, d2, 0, 0, "");
		Destinataire d3 = new Destinataire("Ridel Nicolas",
				"76 rue de l'église", "76190", "Auzebosc", "", "0640191362",
				"0640191362");
		d3.setCoordGPS(new CoordGPS(d3.getRue() + " " + d3.getCp() + " "
				+ d3.getVille(), this));
		Livraison l3 = new Livraison("505465654", null, d3, 0, 0, "");
		Destinataire d4 = new Destinataire("Pierre Ruggirello", "", "27200",
				"Vernon", "", "0640191362", "0640191362");
		d4.setCoordGPS(new CoordGPS(d4.getRue() + " " + d4.getCp() + " "
				+ d4.getVille(), this));
		Livraison l4 = new Livraison("505465654", null, d4, 0, 0, "");
		ArrayList<Livraison> list = new ArrayList<Livraison>();
		list.add(l1);
		list.add(l2);
		list.add(l3);
		list.add(l4);
		Tournee t = Tournee.getInstance();

		AntExecution ae = new AntExecution(t.getListeLivraison());

		//t.setListeLivraison(ae.run());

		leaveButton = (Button) findViewById(R.id.leave);
		scanButton = (Button) findViewById(R.id.scan_parcel);
		showListButton = (Button) findViewById(R.id.show_list);

		scanButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent("com.guigou.projet.android.SCAN");
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
				Intent i = new Intent(ProjetandroidActivity.this, ShowLivraison.class);
				startActivity(i);
			}
		});

		leaveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ProjetandroidActivity.this.finish();
			}
		});
		

		/*
		 * AlertDialog.Builder adb = new AlertDialog.Builder(this);
		 * adb.setTitle("Google Map"); adb.setMessage(coord[0] +
		 * "Impossible de trouver cette adresse !" + coord[1]);
		 * adb.setPositiveButton("Fermer", null); adb.show();
		 */
	}

	@Override
	protected void onResume() {
		super.onResume();
		Utils.checkGPS(this);
	}
}