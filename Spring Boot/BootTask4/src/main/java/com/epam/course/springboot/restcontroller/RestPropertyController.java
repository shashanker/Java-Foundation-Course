package com.epam.course.springboot.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.course.springboot.entity.Agent;
import com.epam.course.springboot.entity.Property;
import com.epam.course.springboot.enums.Status;
import com.epam.course.springboot.repository.AgentRepository;
import com.epam.course.springboot.repository.PropertyRepository;
import com.epam.course.springboot.request.PropertyRequest;
import com.epam.course.springboot.response.PropertyResponse;

@RestController
@CrossOrigin
public class RestPropertyController {

	@Autowired
	PropertyRepository propertyRepository;

	@Autowired
	AgentRepository agentRepository;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(path = "/api/createproperty", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PropertyResponse> createProperty(
			@RequestBody PropertyRequest propertyRequest) {

		propertyRequest.setStatus(Status.OPEN);
		Property property = conversionService.convert(propertyRequest,
				Property.class);

		propertyRepository.save(property);
		
		Agent agent = agentRepository.findById(propertyRequest.getAgentId());
		agent.addProperty(property);
		property.setAgent(agent);

		/*
		 * Agent agent = agentRepository.findById(propertyRequest.) RoomEntity
		 * roomEntity = roomRepository
		 * .findById(reservationRequest.getRoomId());
		 * roomEntity.addReservationEntity(reservationEntity);
		 * roomRepository.save(roomEntity);
		 * 
		 * reservationEntity.setRoomEntity(roomEntity);
		 */
		PropertyResponse propertyResponse = conversionService.convert(property,
				PropertyResponse.class);

		return new ResponseEntity<>(propertyResponse, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/api/test", method = RequestMethod.GET)
	public ResponseEntity<String> test() {
		return new ResponseEntity<>("test successfull", HttpStatus.OK);
	}

	@RequestMapping(path = "/api/allprops", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<PropertyResponse>> getProperties() {
		List<PropertyResponse>  list = new ArrayList<>();
			propertyRepository.findAll().forEach(p -> {
				list.add(conversionService.convert(p,
						PropertyResponse.class));
			});;
			return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
}
