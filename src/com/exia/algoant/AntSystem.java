package com.exia.algoant;

import java.util.ArrayList;

public class AntSystem {
	int pathCount;
	Problem data;
	ArrayList<Ant> ants = new ArrayList<Ant>();
	ArrayList<Integer> bestSolution= new ArrayList<Integer>();
	int bestLength;
	int curIteration;
	
	AntSystem(int nbAnt, Problem d)
	{
		data = d;
		for (int i=0; i<nbAnt; i++)
	        ants.add(new Ant(data));
		bestLength = 999999;
		pathCount = 0;
		curIteration = 0;		
	}
	
	AntSystem()
	{
		for (int i = 0; i < ants.size(); i++)
	        ants.remove(i);	
	}

	public void run(int n){        
	    for (curIteration=0; curIteration<n; curIteration++){
	        // process each ant
	        for(int i = 0; i < ants.size(); i++)
	        {
		        
		            try{
		                 ants.get(i).frame();
		            }catch(AntException e){
						if (e.status == AntException.TO_REGISTER)
		                    notifySolution(e.a.tmpVisitedLength, e.a.visitedCities);      
	
		                if(bestLength <= data.optimalLength)
		                     return;                
	
						Ant it = new Ant(data);       
						ants.set(i, it);                                           
		            }		             
	        }
	       
	        // evaporate the pheromones
			if (curIteration % 20 == 0)
				data.evaporate();  

			//if (curIteration%50==0)
				//System.out.println("Test : " + bestLength);
	    }
	}
	
	public void notifySolution(long value, ArrayList<Integer> cities){
	    if (bestLength == -1){
	        bestLength  = (int)value;
	        bestSolution = cities;
	    }else{
			pathCount ++;

			if (value < bestLength){
				bestLength = (int)value;
				bestSolution = cities;

				//std::cout << curIteration << " " << bestLength << " ";
				/*for (int i=0; i<int(cities.size()); i++)
					std::cout << cities[i] << ",";
	*/
				//std::cout << std::endl;
			}
	    }
	}
}
