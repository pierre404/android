package com.exia.algoant;

import java.util.ArrayList;

import com.classes.projet.CoordGPS;
import com.classes.projet.Livraison;

public class AntExecution {

	private ArrayList<Livraison> livraisons;
	private int distances[][];

	private double latRepository = 49.477029;
	private double lonRepository = 1.091308;
	
	private boolean needReturn = true;

	private CoordGPS coordGPSRepository;

	public AntExecution(ArrayList<Livraison> livraisons, boolean needReturn) {
		this.livraisons = livraisons;
		this.needReturn = needReturn;
		coordGPSRepository = new CoordGPS(latRepository, lonRepository);
	}

	public ArrayList<Livraison> run() {
		distances = new int[livraisons.size() + 1][livraisons.size() + 1];
		CoordGPS coordGPSa;
		CoordGPS coordGPSb;
		int posRepository = 0;
		int startIndex = 1;
		if(!needReturn)
		{
			posRepository = livraisons.size();
			startIndex = 0;
		}
		for (int i = 1; i < livraisons.size() + 1; i++) {
			for (int j = 1; j < livraisons.size() + 1; j++) {
				if (livraisons.get(i - 1).getDestinataire() == null) {
					coordGPSa = livraisons.get(i - 1).getExpediteur()
							.getCoordGPS();

				} else {
					coordGPSa = livraisons.get(i - 1).getDestinataire()
							.getCoordGPS();
				}
				if (livraisons.get(j - 1).getDestinataire() == null) {
					coordGPSb = livraisons.get(j - 1).getExpediteur()
							.getCoordGPS();

				} else {

					coordGPSb = livraisons.get(j - 1).getDestinataire()
							.getCoordGPS();
				}
				distances[i][j] = getDistance(coordGPSa, coordGPSb);
			}
		}

		for (int i = 1; i < livraisons.size() + 1; i++) {
			if (livraisons.get(i - 1).getDestinataire() == null) {
				distances[0][i] = distances[i][0] = getDistance(
						coordGPSRepository, livraisons.get(i - 1)
								.getExpediteur().getCoordGPS());
			} else {
				distances[0][i] = distances[i][0] = getDistance(
						coordGPSRepository, livraisons.get(i - 1)
								.getDestinataire().getCoordGPS());
			}
		}

		int nbVilles = distances.length, nbIterations = 5000, nbFourmis = 200;
		Problem p = new Problem(nbVilles, 5000, 1, (float) .05, distances);
		p.needreturn = needReturn;
		AntSystem sys = new AntSystem(nbFourmis, p);
		sys.run(nbIterations);
		ArrayList<Livraison> orderLivraisons = new ArrayList<Livraison>();
		for (int i = 1; i < livraisons.size() + 1; i++) {
			orderLivraisons.add(livraisons.get(sys.bestSolution.get(i) - 1));
		}
		return orderLivraisons;
	}

	public int getDistance(CoordGPS coordGPSA, CoordGPS coordGPSB) {
		double la1 = (coordGPSA.getLatitude() * Math.PI) / 180;
		double la2 = (coordGPSB.getLatitude() * Math.PI) / 180;
		double lon1 = (coordGPSA.getLongitude() * Math.PI) / 180;
		double lon2 = (coordGPSB.getLongitude() * Math.PI) / 180;

		return (int) (6371 * Math.acos(Math.sin(la1) * Math.sin(la2)
				+ Math.cos(la1) * Math.cos(la2) * Math.cos(lon1 - lon2)));
	}
}
