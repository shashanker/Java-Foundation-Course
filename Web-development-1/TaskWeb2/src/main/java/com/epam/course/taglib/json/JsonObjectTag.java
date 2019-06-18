package com.epam.course.taglib.json;

import java.io.StringWriter;
import java.util.Stack;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

import com.epam.course.taglib.json.util.JSONObject;

public class JsonObjectTag extends JsonBaseTag {
	public JsonObjectTag() {
	}

	public void doTag() throws JspException {
		JspFragment body = getJspBody();
		Stack stack = getEntityStack();

		if ((!stack.isEmpty()) && (getCurrentEntity().isArray()) && (getName() != null)) {
			throw new JspException(Messages.getString("com.epam.course.taglib.json.error.object.4"));
		}

		try {
			JsonEntity entity = new JsonEntity(new JSONObject());

			stack.push(entity);

			if (body != null) {
				StringWriter writer = new StringWriter();
				body.invoke(writer);
			}

			stack.pop();

			if ((!stack.isEmpty()) && ((getCurrentEntity().getWrappedObject() instanceof JSONObject))
					&& ((getName() == null) || (getName().trim().length() == 0))) {
				throw new JspException(Messages.getString("com.epam.course.taglib.json.error.object.2"));
			}

			processTagEnd(entity);

		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException(Messages.getString("com.epam.course.taglib.json.error.object.0"), e);
		}
	}
}
