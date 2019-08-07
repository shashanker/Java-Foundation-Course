package com.epam.course.springboot.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.course.springboot.domain.Position;
import com.epam.course.springcore.util.Util;


@Service("PositionService")
public class PositionService implements CompanyService{
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	/*
	 * List available position
	 */
	@SuppressWarnings("finally")
	public Map<String, Integer> getAvailablePositions()
	{
		logger.debug("getAvailablePositions method called!");
		try {
			System.out.println("****Available Positions ******");
			for(Entry<String, Integer> e : Util.positionMap.entrySet()) {
				System.out.println("Position : "+e.getKey()+"\t vacancies : "+e.getValue());
			}
			
			return Util.positionMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in getAvailablePositions() :"+ e.getMessage());
		}
		finally {
			return null;
		}
	}
	
	/*
	 * Insert a position into List of 
	 * available positions
	 */
	@SuppressWarnings("finally")
	public Position insertPosition(String pname) {
		logger.debug("insertPosition method called!");
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in insertPosition() :"+ e.getMessage());
		}
		finally {
			return null;
		}
	}

	/*
	 * Delete position from list of 
	 * available positions
	 */
	public void deletePosition(String pname) {
		
	//	getAvailablePositions();
		
		logger.debug("deletePosition method called!");
		int noOfPoistions = 0;
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in deletePosition() :"+ e.getMessage());
		}
		
	}
	
	public void updatePosition(String iValue , String uValue) {
		
		//getAvailablePositions();
		logger.debug("updatePosition method called!");
		try {
			Position position = null;
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in updatePosition() :"+ e.getMessage());
		}
	}
	
	
	public static PositionService getPositionService()
	{
		return new PositionService();
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("Servicing Request by PositionService....");
	}
}
