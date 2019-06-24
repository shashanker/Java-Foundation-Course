package com.epam.course.springcore.service;

import java.util.ArrayList;
import java.util.List;

import com.epam.course.springcore.domain.Horse;

public class FinishLineService {
	
	List<Horse> ranking = new ArrayList<>();
	public void arrived(Horse horse) {
		// TODO Auto-generated method stub
		ranking.add(horse);
	}
	
	public void print() {
		// TODO Auto-generated method stub
		
		System.out.println("*******Standings*****");
		int i = 1;
		for(Horse horse : ranking)
		{
			System.out.println(i+". "+horse.getName());
			i++;
		}

	}

	public List<Horse> getRanking() {
		return ranking;
	}

	public void setRanking(List<Horse> ranking) {
		this.ranking = ranking;
	}

}
