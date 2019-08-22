package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.hibernate.model.Project;
import com.epam.course.hibernate.request.ProjectRequest;

public class ProjectRequestToProjectEntityConverter
		implements Converter<ProjectRequest, Project> {

	@Override
	public Project convert(ProjectRequest source) {
		// TODO Auto-generated method stub
		Project project = new Project();
		project.setName(source.getName());
		if(source.getId()!=null) {
			project.setId(source.getId());
		}
		
		return project;
	}

}
