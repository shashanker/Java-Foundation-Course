package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.springboot.entity.Agent;
import com.epam.course.springboot.request.AgentRequest;

public class AgentRequestToAgentEntityConverter
		implements Converter<AgentRequest, Agent> {

	@Override
	public Agent convert(AgentRequest source) {
		// TODO Auto-generated method stub
		Agent agent = new Agent();
		agent.setName(source.getName());
		agent.setPhone(source.getPhone());
		agent.setBio(source.getBio());
		agent.setMobile(source.getMobile());
		agent.setEmail(source.getEmail());
		agent.setSkype(source.getSkype());
		agent.setPic(source.getPic());
		return agent;
	}

}
