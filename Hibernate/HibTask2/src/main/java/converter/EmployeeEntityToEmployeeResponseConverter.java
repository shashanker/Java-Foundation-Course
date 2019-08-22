package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.hibernate.model.Employee;
import com.epam.course.hibernate.response.EmployeePersonalInfoResponse;
import com.epam.course.hibernate.response.EmployeeResponse;


public class EmployeeEntityToEmployeeResponseConverter
		implements Converter<Employee, EmployeeResponse> {
	

	@Override
	public EmployeeResponse convert(Employee source) {
		// TODO Auto-generated method stub
		EmployeeResponse employeeResponse = new EmployeeResponse();
		/*reservationResponse.setCheckin(source.getCheckin());
		reservationResponse.setCheckout(source.getCheckout());
		reservationResponse.setId(source.getId());*/
	/*	employeeResponse.setId(source.getId());
		employeeResponse.setName(source.getName());
		employeeResponse.setGender(source.getGender());
		employeeResponse.setDob(source.getDob());
		employeeResponse.setAddress(source.getAddress().toString());*/
		employeeResponse.setId(source.getId());
		employeeResponse.setName(source.getName());
		employeeResponse.setAddress(source.getAddress());
			EmployeePersonalInfoResponse infoResponse = new EmployeePersonalInfoResponse();
			infoResponse.setCurrentAddress(source.getPersonalInfo().getCurrentAddress());
			infoResponse.setMobile(source.getPersonalInfo().getMobile());
			infoResponse.setPermenantAdress(source.getPersonalInfo().getPermenantAdress());
			infoResponse.setPersonalEmail(source.getPersonalInfo().getPersonalEmail());
		employeeResponse.setPersonalInfo(infoResponse);
		employeeResponse.setExternal(source.isExternal());
		employeeResponse.setStatus(source.getEmpStatus().toString());
		return employeeResponse;
	}

}
