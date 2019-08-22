package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.hibernate.enums.Gender;
import com.epam.course.hibernate.model.Address;
import com.epam.course.hibernate.model.Employee;
import com.epam.course.hibernate.request.EmployeeRequest;

public class EmployeeRequestToEmployeeEntityConverter
		implements Converter<EmployeeRequest, Employee> {

	@Override
	public Employee convert(EmployeeRequest source) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		/*reservationEntity.setCheckin(source.getCheckin());
		reservationEntity.setCheckout(source.getCheckout());

		if (null != source.getId()) {
			reservationEntity.setId(source.getId());
		}*/
		employee.setName(source.getName());
		employee.setGender(Gender.valueOf(source.getGender()));
		employee.setDob(source.getDob());
		employee.setAddress(new Address(source.getStreet(), source.getCity(), source.getZip(), source.getState()));
		
		return employee;
	}

}
