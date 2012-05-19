package com.exia.android;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.classes.projet.Livraison;
import com.classes.projet.Tournee;
import com.exia.maps.CustomItemizedOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsActivity extends MapActivity {

	private MapView mv = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);

		mv = (MapView) findViewById(R.id.mapView);

		drawAddressesToLocation(Tournee.getInstance().getListeLivraison());

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private void drawAddressesToLocation(ArrayList<Livraison> livraisons) {

		List<Overlay> mapOverlays = mv.getOverlays();
		mapOverlays.removeAll(mapOverlays);
		Drawable drawableBlue = this.getResources().getDrawable(
				R.drawable.marker);
		Drawable drawableRed = this.getResources().getDrawable(
				R.drawable.marker2);
		CustomItemizedOverlay itemizedoverlay = new CustomItemizedOverlay(
				drawableBlue, this);

		int latitude;
		int longitude;
		for (int i = 0; i < livraisons.size(); i++) {
			if(livraisons.get(i).getDestinataire() == null)
			{
				latitude = (int) (livraisons.get(i).getExpediteur().getCoordGPS().getLatitude() * 1000000);
				longitude = (int) (livraisons.get(i).getExpediteur().getCoordGPS().getLongitude() * 1000000);
			}
			else
			{
				latitude = (int) (livraisons.get(i).getDestinataire().getCoordGPS().getLatitude() * 1000000);
				longitude = (int) (livraisons.get(i).getDestinataire().getCoordGPS().getLongitude() * 1000000);
			}
			GeoPoint point = new GeoPoint(latitude, longitude);

			OverlayItem overlayitem = new OverlayItem(point, String.valueOf(i + 1), "");
			itemizedoverlay.addOverlay(overlayitem);

			if (livraisons.get(i).getCoordGPS() != null) {
				drawableRed.setBounds(
					    0 - drawableRed.getIntrinsicWidth() / 2, 0 - drawableRed.getIntrinsicHeight(), 
					    drawableRed.getIntrinsicWidth() / 2, 0);
				overlayitem.setMarker(drawableRed);
			}
		}

		/*
		 * GeoPoint point = new GeoPoint( (int) (addresses.get(0).getLatitude()
		 * * 1000000), (int) (addresses.get(0).getLongitude() * 1000000));
		 * 
		 * mapOverlays.add(itemizedoverlay);
		 */

		mapOverlays.add(itemizedoverlay);
		MapController controller = mv.getController();
		controller.zoomToSpan(itemizedoverlay.getLatSpanE6(),
				itemizedoverlay.getLonSpanE6());
		// use the newly defined getCenter method
		controller.setCenter(itemizedoverlay.getCenter());

		mv.invalidate();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	
	
}
