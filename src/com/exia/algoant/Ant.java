package com.exia.algoant;

import java.util.ArrayList;

public class Ant {	
	
    long currentArcSize;
    long currentArcPos;
    int currentDestination;
    int currentOrigin;
    
    ArrayList<Integer> visitedCities = new ArrayList<Integer>();
    ArrayList<Integer> citiesStillToVisit = new ArrayList<Integer>();
    
    long tmpVisitedLength;
    

    final static int NOTHING = 1;
    final static int SEARCHING_PATH = 2;
    final static int RETURNING = 3;
    
    int status = 0;

    Problem data;
	
	Ant(Problem d)
	{
		data = d;
		tmpVisitedLength = 0;
	    currentArcPos = -1;
	    currentDestination = 0;
	    currentOrigin = 0;
	    status = NOTHING;
	    
	    for (int i=0; i<data.nbCities; i++)
	        citiesStillToVisit.add(i);
	}
	
	public void frame() throws AntException
	{
		switch(status){
        case SEARCHING_PATH:
            tmpVisitedLength ++;
        case RETURNING:
            currentArcPos++;               
            if (currentArcPos >= currentArcSize)
                findNextSearchDestination();
            break;                                       
        case NOTHING:
            findNextSearchDestination();
            break;                
    }
	}
	
	public void findNextSearchDestination() throws AntException {
		switch(status){
			case NOTHING:{
				visitedCities.add(0);
				for(int i = 0; i < citiesStillToVisit.size(); i++)
				{
					if(citiesStillToVisit.get(i) == 0)
					{
						citiesStillToVisit.remove(i);
					}
				}
				
				int dest = getNearCity(0);
				status = SEARCHING_PATH;
				currentOrigin = 0;
				currentDestination = dest;
				currentArcPos = 0;
				currentArcSize = data.distances[0][currentDestination];

				break;
			}
			case SEARCHING_PATH:{
				// on a atteint currentDestination
	            
				visitedCities.add( currentDestination );  

				/*std::vector<int>::iterator tmp = citiesStillToVisit.begin();
				while (tmp != citiesStillToVisit.end()){
					if (*tmp == currentDestination){
						citiesStillToVisit.erase(tmp);
						break;
					}
					tmp++;	
				}*/
				
				for(int i = 0; i < citiesStillToVisit.size(); i++)
				{
					if(citiesStillToVisit.get(i) == currentDestination)
					{
						citiesStillToVisit.remove(i);
					}
				}

				//std::remove(citiesStillToVisit.begin(), citiesStillToVisit.end(), currentDestination );
	            //citiesStillToVisit.resize(citiesStillToVisit.size() -1);

	            if (citiesStillToVisit.size() == 0){
	                // plus rien à visiter, le chemin est complet
	                // on revient vers le nid
	            	if(data.needreturn)
	            		tmpVisitedLength += data.distances[currentDestination][0];
	            	else
	            		tmpVisitedLength += data.distances[currentDestination][data.nbCities];

					status = RETURNING;
	                currentOrigin =  visitedCities.size()-1;
	                currentDestination = visitedCities.size()-2;    
	                currentArcSize = data.distances[ visitedCities.get(currentOrigin) ][ visitedCities.get(currentDestination) ];
	                currentArcPos = currentArcSize;       
	                return;                                
	            }

				int dest = getNearCity(currentDestination);
				currentOrigin = currentDestination;
				currentDestination = dest;			
				currentArcSize = data.distances[currentOrigin][currentDestination];
				currentArcPos = 0;
				break;
			}
			case RETURNING:{
				if (currentDestination == 0){
					// retourné au nid avec succès
					data.setPheromones(visitedCities.get(currentOrigin), visitedCities.get(currentDestination), tmpVisitedLength);

					// sauver le résultat, changer de fourmi
					AntException e = new AntException();
					e.a = this;
					e.status = AntException.TO_REGISTER;
					
					throw e;
	            }
	           
	            // trouver la ville précédemment visitée et la passer en destination
	            // mettre des phéromones sur l'arc parcouru
	            data.setPheromones(visitedCities.get(currentOrigin), visitedCities.get(currentDestination), tmpVisitedLength );
	            currentOrigin = currentDestination;
	            currentDestination --;           
	            currentArcSize = data.distances[ visitedCities.get(currentOrigin) ][ visitedCities.get(currentDestination) ];                                          
				currentArcPos = currentArcSize;
				
				break;
			}
		}	
	}
	
	public int getNearCity(int from) throws AntException{
	    // roulette sur les chemins restants, pondérés par les phéromones
	    float pheromoneSize = 0;           
		for (int i = 0; i < citiesStillToVisit.size(); i++){
			if (citiesStillToVisit.get(i) == from)
				continue;
			pheromoneSize  += data.pheromones[from][ citiesStillToVisit.get(i) ];
		}

	    float found = (float)(Math.random() * 100%(int)(pheromoneSize*10))/(float)(10);
	    float tmpPheromones = 0;
	    int ii = 0;
	    while (ii < citiesStillToVisit.size()){
			if (citiesStillToVisit.get(ii) == from){
				ii++;
				continue;
			}

	        tmpPheromones  += data.pheromones[currentDestination][ citiesStillToVisit.get(ii) ];
	   
	        if (tmpPheromones   > found)
	            break;

	        ii ++;
	    }
	    if (ii == citiesStillToVisit.size()){
			// aucune solution acceptable, détruire la fourmi courante
	    	AntException e = new AntException();
			e.a = this;
			e.status = AntException.TO_DELETE;
			throw e;
	    }

		return citiesStillToVisit.get(ii);
	}


}
