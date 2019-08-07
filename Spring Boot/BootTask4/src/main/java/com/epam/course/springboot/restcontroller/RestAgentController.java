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
import com.epam.course.springboot.repository.AgentRepository;
import com.epam.course.springboot.repository.PropertyRepository;
import com.epam.course.springboot.request.AgentRequest;
import com.epam.course.springboot.response.AgentResponse;

@RestController
@CrossOrigin
public class RestAgentController {

	@Autowired
	PropertyRepository propertyRepository;

	@Autowired
	AgentRepository agentRepository;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(path = "/api/createAgent", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgentResponse> createAgent(
			@RequestBody AgentRequest agentRequest) {

		
		Agent agent = conversionService.convert(agentRequest, Agent.class);
		agentRepository.save(agent);
	/*	propertyRequest.setStatus(Status.OPEN);
		Property property = conversionService.convert(propertyRequest,
				Property.class);

		propertyRepository.save(property);
*/
		/*
		 * Agent agent = agentRepository.findById(propertyRequest.) RoomEntity
		 * roomEntity = roomRepository
		 * .findById(reservationRequest.getRoomId());
		 * roomEntity.addReservationEntity(reservationEntity);
		 * roomRepository.save(roomEntity);
		 * 
		 * reservationEntity.setRoomEntity(roomEntity);
		 */
		AgentResponse agentResponse = conversionService.convert(agent,
				AgentResponse.class);

		return new ResponseEntity<>(agentResponse, HttpStatus.CREATED);
	}


	@RequestMapping(path = "/api/allAgents", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AgentResponse>> getAgents() {
		List<AgentResponse>  list = new ArrayList<>();
			agentRepository.findAll().forEach(a -> {
				list.add(conversionService.convert(a,
						AgentResponse.class));
			});;
			return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
}
