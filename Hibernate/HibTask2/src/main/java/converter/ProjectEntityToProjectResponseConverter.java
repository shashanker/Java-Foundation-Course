package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.hibernate.model.Project;
import com.epam.course.hibernate.response.ProjectResponse;


public class ProjectEntityToProjectResponseConverter
		implements Converter<Project, ProjectResponse> {
	

	@Override
	public ProjectResponse convert(Project source) {
		// TODO Auto-generated method stub
		ProjectResponse projectResponse = new ProjectResponse();
		projectResponse.setName(source.getName());
		projectResponse.setId(source.getId());
		return projectResponse;
	}

}
