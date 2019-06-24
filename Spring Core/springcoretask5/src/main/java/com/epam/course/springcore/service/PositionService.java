package com.epam.course.springcore.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.OptionalInt;

import org.springframework.stereotype.Service;

import com.epam.course.springcore.domain.Position;
import com.epam.course.springcore.util.Util;

@Service
public class PositionService {
	
	/*
	 * List available position
	 */
	public void getAvailablePositions()
	{
		System.out.println("****Available Positions ******");
		for(Entry<String, Integer> e : Util.positionMap.entrySet()) {
			System.out.println("Position : "+e.getKey()+"\t vacancies : "+e.getValue());
		}
	}
	
	/*
	 * Insert a position into List of 
	 * available positions
	 */
	public Position insertPosition(String pname) {
		
		int maxID = 1;
		OptionalInt opt = Util.positions.stream().mapToInt(p -> p.getPid()).max();
		if(opt.isPresent()) {
			maxID = opt.getAsInt() + 1;
		}
		Position p = new Position();
		p.setPid(maxID);
		p.setName(pname);
		Util.positions.add(p);
		
		Util.positionMap = new HashMap<>();
		Util.positions.forEach(pos -> {
			int count = Util.positionMap.get(pos.getName())==null?0:Util.positionMap.get(pos.getName());
			Util.positionMap.put(pos.getName(), ++count);
		});
		
		getAvailablePositions();
		
		return p;
	}

	/*
	 * Delete position from list of 
	 * available positions
	 */
	public void deletePosition(String pname) {
		
	//	getAvailablePositions();
		
		
		int noOfPoistions = 0;
		if(Util.positionMap.get(pname) != null) {
			noOfPoistions = Util.positionMap.get(pname);
			noOfPoistions--;
		}
		else {
			System.out.println("There are no jobs available for position "+pname);
			return;
		}
		if(noOfPoistions==0) {
			Util.positionMap.remove(pname);
		}else
		Util.positionMap.put(pname, noOfPoistions);
		Position removePos = Util.positions.stream().filter(p -> p.getName().equals(pname)).findFirst().get();
		Util.positions.remove(removePos);
		
		getAvailablePositions();
		
		
	}
	
	public void updatePosition(String iValue , String uValue) {
		
		//getAvailablePositions();
		
		
		Util.positions.forEach(p -> {
			if(p.getName().equalsIgnoreCase(iValue)) {
				p.setName(uValue);
			}
		});
		
		Util.positionMap = new HashMap<>();
		Util.positions.forEach(pos -> {
			int count = Util.positionMap.get(pos.getName())==null?0:Util.positionMap.get(pos.getName());
			Util.positionMap.put(pos.getName(), ++count);
		});
		
		getAvailablePositions();
	}
}
