package com.exia.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
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

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Destinataire d1 = new Destinataire("Mouquet Benoit", "9, rue du beau soleil", "76660", "Londinières", "", "0640191362", "0640191362");
		d1.setCoordGPS(new CoordGPS(d1.getRue() + " " + d1.getCp() + " " + d1.getVille()));
		Livraison l1 = new Livraison("505465654", null, null, d1, 0, 0, "");
		Destinataire d2 = new Destinataire("Beuvin Juliette", "1, rue victor Boucher", "76270", "Neufchatel en Bray", "", "0640191362", "0640191362");
		Livraison l2 = new Livraison("505465654", null, null, d2, 0, 0, "");
		ArrayList<Livraison> list = new ArrayList<Livraison>();
		list.add(l1);
		list.add(l2);
		Tournee t = new Tournee(null, null, list);
		
		AntExecution ae = new AntExecution(t.getListeLivraison());
		
		

		leaveButton = (Button) findViewById(R.id.leave);
		scanButton = (Button) findViewById(R.id.scan_parcel);

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

	public static Context getAppContext() {
		return ProjetandroidActivity.getAppContext();
	}
}