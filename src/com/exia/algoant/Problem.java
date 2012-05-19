package com.exia.algoant;

public class Problem {

	int nbCities;
	float borneMax, borneMin;
	float evaporation;
	
	boolean needreturn = true;

	int optimalLength;

	int distances[][] = null;
	float pheromones[][] = null;

	Problem(int nbVilles, float borne1, float borne2, float coeff, int[][]distances) {
		nbCities = nbVilles;
		borneMax = borne1;
		borneMin = borne2;
		evaporation = coeff;

		this.distances = distances;
		pheromones = new float[nbVilles][nbVilles];

		for (int i = 0; i < nbCities; i++) {
			for (int j = 0; j < nbCities; j++) {
				//distances[i][j] = 0;
				pheromones[i][j] = borneMin;
			}
		}

		/*for (int i = 0; i < nbCities; i++) {
			distances[i][i] = 0;
			for (int j = i + 1; j < nbCities; j++)
			{
				distances[i][j] = distances[j][i] = (int) (Math.random()*100 % 100);
			}
		}*/
		
		/*distances[0][0] = 0;
		distances[0][1] = 4;
		distances[0][2] = 5;
		distances[0][3] = 5;
		distances[0][4] = 3;
		distances[1][0] = 4;
		distances[1][1] = 0;
		distances[1][2] = 6;
		distances[1][3] = 5;
		distances[1][4] = 3;
		distances[2][0] = 5;
		distances[2][1] = 6;
		distances[2][2] = 0;
		distances[2][3] = 4;
		distances[2][4] = 3;
		distances[3][0] = 3;
		distances[3][1] = 5;
		distances[3][2] = 4;
		distances[3][3] = 0;
		distances[3][4] = 3;
		distances[4][0] = 3;
		distances[4][1] = 3;
		distances[4][2] = 3;
		distances[4][3] = 3;
		distances[4][4] = 0;*/
		
				

		// solution optimale
		/*for (int i = 0; i < nbCities; i++)
			distances[i][(i + 1) % nbCities] = distances[(i + 1) % nbCities][i] = 1;*/

		optimalLength = nbCities;
		//System.out.println(optimalLength);
	}

	public void setPheromones(int i, int j, long wayLength) {
		float ph = 100 * optimalLength
				/ (float) (wayLength + 1 - optimalLength);

		pheromones[i][j] += ph;

		if (pheromones[i][j] < borneMin)
			pheromones[i][j] = borneMin;
		if (pheromones[i][j] > borneMax)
			pheromones[i][j] = borneMax;

		pheromones[j][i] = pheromones[i][j];
	}
	
	public void evaporate(){
	    for (int i=0; i<nbCities; i++)
	        for (int j=0; j<i; j++){
	            pheromones[i][j] = (float)(pheromones[i][j]*(100-evaporation) /100);
	            if ((int)(pheromones[i][j]) < borneMin)
	                pheromones[i][j] = (float)(borneMin);

	            pheromones[j][i] = pheromones[i][j];                
	        }
	}

}
