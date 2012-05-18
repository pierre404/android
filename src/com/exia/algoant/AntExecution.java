package com.exia.algoant;

import java.util.ArrayList;
import com.classes.projet.Livraison;

public class AntExecution {

	private ArrayList<Livraison> livraisons;
	private int distances[][];

	public AntExecution(ArrayList<Livraison> livraisons) {
		this.livraisons = livraisons;
	}

	public ArrayList<Livraison> run() {
		for (int i = 0; i < livraisons.size(); i++) {
			for (int j = 0; j < livraisons.size(); j++) {
				distances[i][j] = (int) Math
						.sqrt(Math.pow((livraisons.get(j).getDestinataire()
								.getCoordGPS().getLatitude() - livraisons
								.get(i).getDestinataire().getCoordGPS()
								.getLatitude()), 2)
								+ Math.pow(livraisons.get(j).getDestinataire()
										.getCoordGPS().getLongitude()
										- livraisons.get(i).getDestinataire()
												.getCoordGPS().getLongitude(),
										2));
			}
		}

		int nbVilles = 4, nbIterations = 500, nbFourmis = 200;
		Problem p = new Problem(nbVilles, 5000, 1, (float) .05, distances);
		AntSystem sys = new AntSystem(nbFourmis, p);
		sys.run(nbIterations);
		ArrayList<Livraison> orderLivraisons = new ArrayList<Livraison>();
		for (int i = 0; i < livraisons.size(); i++) {
			orderLivraisons.add(livraisons.get(sys.bestSolution.get(i)));
		}
		return orderLivraisons;
	}
}
