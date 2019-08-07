package converter;

import org.springframework.core.convert.converter.Converter;

import com.epam.course.springboot.entity.Property;
import com.epam.course.springboot.request.PropertyRequest;

public class PropertyRequestToPropertyEntityConverter
		implements Converter<PropertyRequest, Property> {

	@Override
	public Property convert(PropertyRequest source) {
		// TODO Auto-generated method stub
		Property property = new Property();
		/*reservationEntity.setCheckin(source.getCheckin());
		reservationEntity.setCheckout(source.getCheckout());*/
		property.setTitle(source.getTitle());
		property.setAddress1(source.getAddr1());
		property.setAddress2(source.getAddr2());
		property.setDesc(source.getDesc());
		property.setPrice(source.getPrice());
		property.setType(source.getType());
		//property.setAgent(source.getAgent();
		property.setStatus(source.getStatus());
		property.setArea(source.getArea());
		property.setBeds(source.getBeds());
		property.setBaths(source.getBaths());
		property.setGarages(source.getGarages());
		property.setImgUrl(source.getImgUrl());
		if (null != source.getId()) {
			property.setId(source.getId());
		}
		return property;
	}

}
