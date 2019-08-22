package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.hibernate.model.Unit;
import com.epam.course.hibernate.response.UnitResponse;


public class UnitEntityToUnitResponseConverter
		implements Converter<Unit, UnitResponse> {
	

	@Override
	public UnitResponse convert(Unit source) {
		// TODO Auto-generated method stub
		UnitResponse unitResponse = new UnitResponse();
		unitResponse.setName(source.getName());
		unitResponse.setId(source.getId());
		return unitResponse;
	}

}
