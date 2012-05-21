package com.exia.maps;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.exia.android.MapsActivity;
import com.exia.android.ShowLivraison;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class CustomItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();

	private MapsActivity context;

	public CustomItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public CustomItemizedOverlay(Drawable defaultMarker, Context context) {
		this(defaultMarker);
		this.context = (MapsActivity) context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mapOverlays.get(i);
	}

	@Override
	public int size() {
		return mapOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
		if(index < mapOverlays.size() - 2)
		{
			Intent i = new Intent(context, ShowLivraison.class);
			i.putExtra("indexDelivery", index);
			context.startActivityForResult(i, 1);
		}
		return true;
	}

	public void addOverlay(OverlayItem overlay) {
		mapOverlays.add(overlay);
		this.populate();
	}

	@Override
	public GeoPoint getCenter() {

		if (mapOverlays.size() == 0) {
			return new GeoPoint(0, 0);
		}

		int minLatitude = Integer.MAX_VALUE;
		int maxLatitude = Integer.MIN_VALUE;
		int minLongitude = Integer.MAX_VALUE;
		int maxLongitude = Integer.MIN_VALUE;

		for (OverlayItem overlay : mapOverlays) {
			int lat = overlay.getPoint().getLatitudeE6();
			int lon = overlay.getPoint().getLongitudeE6();
			maxLatitude = Math.max(lat, maxLatitude);
			maxLongitude = Math.max(lon, maxLongitude);
			minLatitude = Math.min(lat, minLatitude);
			minLongitude = Math.min(lon, minLongitude);
		}

		return new GeoPoint((maxLatitude + minLatitude) / 2,
				(maxLongitude + minLongitude) / 2);
	}

	/*@Override
	public void draw(android.graphics.Canvas canvas, MapView mapView,
			boolean shadow) {
		super.draw(canvas, mapView, shadow);

		if (shadow == false) {
			// cycle through all overlays
			for (int index = 0; index < mapOverlays.size(); index++) {
				OverlayItem item = mapOverlays.get(index);

				// Converts lat/lng-Point to coordinates on the screen
				GeoPoint point = item.getPoint();
				Point ptScreenCoord = new Point();
				mapView.getProjection().toPixels(point, ptScreenCoord);

				// Paint
				Paint paint = new Paint();
				paint.setTextAlign(Paint.Align.CENTER);
				paint.setTextSize(50);
				paint.setARGB(255, 255, 255, 255); // alpha, r, g, b (Black,
													// semi
													// see-through)

				// show text to the right of the icon
				canvas.drawText(item.getTitle(), ptScreenCoord.x,
						ptScreenCoord.y - 62, paint);
			}
		}
	}*/

}
