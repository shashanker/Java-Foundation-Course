package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.springboot.entity.Agent;
import com.epam.course.springboot.response.AgentResponse;

public class AgentEntityToAgentResponseConverter 
	implements Converter<Agent, AgentResponse> {

		@Override
		public AgentResponse convert(Agent source) {
			// TODO Auto-generated method stub

			AgentResponse agentResponse = new AgentResponse();
			agentResponse.setId(source.getId());
			agentResponse.setName(source.getName());
			agentResponse.setPhone(source.getPhone());
			agentResponse.setBio(source.getBio());
			agentResponse.setMobile(source.getMobile());
			agentResponse.setEmail(source.getEmail());
			agentResponse.setSkype(source.getSkype());
			agentResponse.setPic(source.getPic());
			return agentResponse;
		}
		
		
}
