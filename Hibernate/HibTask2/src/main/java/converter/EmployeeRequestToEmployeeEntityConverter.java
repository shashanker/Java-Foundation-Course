package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.hibernate.enums.EmployeeStatus;
import com.epam.course.hibernate.model.Employee;
import com.epam.course.hibernate.request.EmployeeRequest;

public class EmployeeRequestToEmployeeEntityConverter
		implements Converter<EmployeeRequest, Employee> {

	@Override
	public Employee convert(EmployeeRequest source) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		if(source.getId()!=null) {
			employee.setId(source.getId());
		}
		employee.setName(source.getName());
		employee.setEmpStatus(EmployeeStatus.valueOf(source.getStatus()));
		employee.setAddress(source.getAddress());
		if(source.getEmployeePersonalInfo()!=null)
		employee.setPersonalInfo(source.getEmployeePersonalInfo());
		employee.setExternal(source.isExternal());
		
		return employee;
	}

}
