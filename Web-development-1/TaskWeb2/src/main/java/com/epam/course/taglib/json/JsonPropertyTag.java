package com.epam.course.taglib.json;

import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

public class JsonPropertyTag extends JsonBaseTag {
	protected Object mValue;

	public JsonPropertyTag() {
	}

	public Object getValue() {
		return mValue;
	}

	public void setValue(Object pValue) {
		mValue = pValue;
	}

	public void doTag() throws JspException {
		JspFragment body = getJspBody();

		if ((getParent() != null) && (getParent().getClass().getName().equals("com.epam.course.taglib.json.JsonPropertyTag"))) {
			throw new JspException(Messages.getString("com.epam.course.taglib.json.error.property.2"));
		}

		if ((!getCurrentEntity().isArray()) && (getName() == null)) {
			throw new JspException(Messages.getString("com.epam.course.taglib.json.error.property.3"));
		}

		try {
			JsonEntity entity = getCurrentEntity();

			Object value;
			if (getValue() == null) {

				if (body == null) {
					value = new String();
				} else {
					StringWriter writer = new StringWriter();
					body.invoke(writer);
					value = writer.toString();
				}

			} else {
				value = getValue();
			}

			 value = trimAndEscapeValue(value);

			entity.add(value, getName());

			resetEscapeXmlValue();

		} catch (JspException e) {

			throw e;
		} catch (Exception e) {
			throw new JspException(Messages.getString("com.epam.course.taglib.json.error.property.1"), e);
		}
	}
}
