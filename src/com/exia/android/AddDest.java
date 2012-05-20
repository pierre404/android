package com.exia.android;

import android.app.Activity;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_dest);
		cancel = (Button) findViewById(R.id.cancel);
		validate = (Button) findViewById(R.id.validate);
		
		nom = (EditText) findViewById(R.id.name);
		rue = (EditText) findViewById(R.id.rue);
		cp = (EditText) findViewById(R.id.cp);
		ville = (EditText) findViewById(R.id.ville);
		telephone = (EditText) findViewById(R.id.telephone);

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		validate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chargement = new ProgressDialog(AddDest.this);
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
						Expediteur e = new Expediteur(nom.getText().toString(), rue.getText().toString(), cp.getText().toString(), ville.getText().toString(), telephone.getText().toString());
						e.setCoordGPS(new CoordGPS(e.getRue() + " " + e.getCp() + " " + e.getVille(), AddDest.this));
						Tournee.getInstance().getListeLivraison().add(new Livraison("", e, null, 0, 0, ""));
						AntExecution ae = new AntExecution(Tournee.getInstance().getListeLivraison(), false, new CoordGPS(Utils.getLocationGPS(AddDest.this)[0], Utils.getLocationGPS(AddDest.this)[1]));
						Tournee.getInstance().setListeLivraison(ae.run());
						handler.sendEmptyMessage(0);
						finish();
					};
				}.start();
			}

		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
