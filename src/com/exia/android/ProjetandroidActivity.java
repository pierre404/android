package com.exia.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProjetandroidActivity extends Activity {
	private Button leaveButton = null;
	private Button scanButton = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

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
}