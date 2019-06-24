package com.epam.course.springcore.service;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.course.springcore.domain.Horse;

public class EmulationService  implements Runnable {
	
	
	private Horse horse;
	
	private long distance;
	
	private FinishLineService finishLineService; 
	
	public Horse getHorse() {
		return horse;
	}

	public void setHorse(Horse horse) {
		this.horse = horse;
	}

	
	
	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	

	public FinishLineService getFinishLineService() {
		return finishLineService;
	}

	public void setFinishLineService(FinishLineService finishLineService) {
		this.finishLineService = finishLineService;
	}

	public void run() {
		// TODO Auto-generated method stub
		long _distance = 0;
		 Random rand = new Random();

	       // int n = rand.nextInt(100) + 1;
		 synchronized (this) {
			 while (_distance < getDistance())
		        {
		            try
		            {
		                Thread.sleep(1000);
		            }
		            catch (Exception e)
		            {
		                e.printStackTrace();
		            }
		            _distance += 10;
		            
		            System.out.println("Distance covered by Horse "+this.getHorse().getName()+" is :"+_distance);
		        }
		        
		        getFinishLineService().arrived(this.getHorse());
		}
	        
		
	}

	

}
