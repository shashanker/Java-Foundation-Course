package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.hibernate.model.Unit;
import com.epam.course.hibernate.request.UnitRequest;

public class UnitRequestToUnitEntityConverter
		implements Converter<UnitRequest, Unit> {

	@Override
	public Unit convert(UnitRequest source) {
		// TODO Auto-generated method stub
		Unit unit = new Unit();
		unit.setName(source.getName());
		if(source.getId()!=null) {
			unit.setId(source.getId());
		}
		
		return unit;
	}

}
