package com.epam.course.springcore.service;

import java.util.List;
import java.util.Random;

import com.epam.course.springcore.domain.Race;
import com.epam.course.springcore.util.Util;

public class RaceService {
	
	
	/*
	 * Return the next available race
	 */
	public Race getRace() {
		List<Race> races = Util.getAllRaces();
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(5);
		//System.out.println("Random number generated is : " + randomInt);
		//System.out.println("selected race :"+races.get(randomInt).getName());
		return races.get(randomInt);
	}

}
