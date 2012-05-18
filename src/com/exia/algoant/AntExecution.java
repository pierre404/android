package com.exia.algoant;

import java.util.ArrayList;

import com.classes.projet.Livraison;

public class AntExecution {

	private ArrayList<Livraison> livraisons;
	private int distances[][];

	private int lat_repository = 49477029;
	private int lon_repository = 1091308;

	public AntExecution(ArrayList<Livraison> livraisons) {
		this.livraisons = livraisons;
	}

	public ArrayList<Livraison> run() {
		distances = new int[livraisons.size() + 1][livraisons.size() + 1];
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

		for (int i = 0; i < livraisons.size(); i++) {
			distances[0][i] = distances[i][0] = (int) Math.sqrt(Math.pow(
					(livraisons.get(i).getDestinataire().getCoordGPS()
							.getLatitude() - lat_repository), 2)
					+ Math.pow(livraisons.get(i).getDestinataire()
							.getCoordGPS().getLongitude()
							- lon_repository, 2));

		}

		int nbVilles = distances.length, nbIterations = 500, nbFourmis = 200;
		Problem p = new Problem(nbVilles, 5000, 1, (float) .05, distances);
		AntSystem sys = new AntSystem(nbFourmis, p);
		sys.run(nbIterations);
		ArrayList<Livraison> orderLivraisons = new ArrayList<Livraison>();
		for (int i = 1; i < livraisons.size() + 1; i++) {
			orderLivraisons.add(livraisons.get(sys.bestSolution.get(i) - 1));
		}
		return orderLivraisons;
	}
}
