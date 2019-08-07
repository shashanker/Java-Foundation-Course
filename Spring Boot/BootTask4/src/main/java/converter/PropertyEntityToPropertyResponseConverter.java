package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.springboot.entity.Property;
import com.epam.course.springboot.response.PropertyResponse;

public class PropertyEntityToPropertyResponseConverter 
	implements Converter<Property, PropertyResponse> {

		@Override
		public PropertyResponse convert(Property source) {
			// TODO Auto-generated method stub

			PropertyResponse propertyResponse = new PropertyResponse();
			/*reservationResponse.setCheckin(source.getCheckin());
			reservationResponse.setCheckout(source.getCheckout());
			reservationResponse.setId(source.getId());*/
			propertyResponse.setId(source.getId());
			propertyResponse.setTitle(source.getTitle());
			propertyResponse.setAddress1(source.getAddress1());
			propertyResponse.setAddress2(source.getAddress2());
			propertyResponse.setDesc(source.getDesc());
			propertyResponse.setPrice(source.getPrice());
			propertyResponse.setType(source.getType());
			propertyResponse.setAgent(source.getAgent());
			propertyResponse.setStatus(source.getStatus().name());
			propertyResponse.setArea(source.getArea());
			propertyResponse.setBeds(source.getBeds());
			propertyResponse.setBaths(source.getBaths());
			propertyResponse.setGarages(source.getGarages());
			propertyResponse.setImgUrl(source.getImgUrl());
			return propertyResponse;
		}
		
		
}
