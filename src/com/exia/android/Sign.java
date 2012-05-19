package com.exia.android;

import java.util.Date;

import com.classes.projet.CoordGPS;
import com.classes.projet.Tournee;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Sign extends Activity {
	
	private Signature s;
	private Button erase;
	private Button validate;
	
	private int indexDelivery;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign);
		
		indexDelivery = getIntent().getExtras().getInt("indexDelivery");
		
		s = (Signature)findViewById(R.id.signature);
		erase = (Button)findViewById(R.id.erase_sign);
		validate = (Button)findViewById(R.id.validate_sign);
		
		erase.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				s.resetSurface();
			}
		});
		
		validate.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				s.saveBitmap(Sign.this);				
				Tournee.getInstance().getListeLivraison().get(indexDelivery).setDate(new Date());
				double[] d = Utils.getLocationGPS(Sign.this);
				Tournee.getInstance().getListeLivraison().get(indexDelivery).setCoordGPS(new CoordGPS(d[0], d[1]));
				setResult(1);
				Sign.this.finish();
			}
		});
	}
	
	@Override
	public void onBackPressed()         
	{
		super.onBackPressed();
		finish();          
    }
}
