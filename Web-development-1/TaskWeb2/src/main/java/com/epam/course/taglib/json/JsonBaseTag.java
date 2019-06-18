package com.epam.course.taglib.json;

import java.util.Stack;
import javax.servlet.ServletContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.course.taglib.json.util.JSONException;

public abstract class JsonBaseTag extends SimpleTagSupport {
	private static final String JSON_OBJECT_STACK_KEY = "com.epam.course.taglib.json.objectStack";
	private static final String PRETTY_PRINT_KEY = "com.epam.course.taglib.json.prettyPrint";
	private static final String ESCAPE_XML_KEY = "com.epam.course.taglib.json.escapeXml";
	private static final String CURRENT_ESCAPE_XML_VALUE_KEY = "com.epam.course.taglib.json.escapeXml.currentValue";
	private static final int JSON_OBJECT_STACK_SCOPE = 2;
	protected static final int PRETTY_PRINT_INDENT = 2;
	private static final boolean ESCAPE_XML_DEFAULT = true;
	private static final boolean TRIM_DEFAULT = true;
	private static final boolean PRETTY_PRINT_DEFAULT = false;
	protected String mName;

	public JsonBaseTag() {
	}

	public String getName() {
		return mName;
	}

	public void setName(String pName) {
		mName = pName;
	}

	protected boolean mTrim = true;

	public boolean getTrim() {
		return mTrim;
	}

	public void setTrim(boolean pTrim) {
		mTrim = pTrim;
	}

	protected boolean mNewStackCreated = false;

	public void setPrettyPrint(boolean pPrettyPrint) {
		getJspContext().setAttribute("com.epam.course.taglib.json.prettyPrint", Boolean.valueOf(pPrettyPrint));
	}

	public int getPrettyPrintIndentFactor() {
		boolean prettyPrint = getBooleanDefaultValue("com.epam.course.taglib.json.prettyPrint", Boolean.valueOf(false));
		return prettyPrint ? 2 : 0;
	}

	protected Boolean mEscapeXmlOriginalValue = null;
	protected boolean mEscapeXmlValueSet = false;

	public boolean getEscapeXml() {
		Boolean escapeXml = (Boolean) getJspContext().getAttribute("com.epam.course.taglib.json.escapeXml.currentValue", 2);

		if (escapeXml == null) {
			escapeXml = getEscapeXmlDefault();
		}
		return escapeXml.booleanValue();
	}

	public void setEscapeXml(boolean pEscapeXml) {
		mEscapeXmlValueSet = true;
		mEscapeXmlOriginalValue = ((Boolean) getJspContext().getAttribute("com.epam.course.taglib.json.escapeXml.currentValue", 2));

		getJspContext().setAttribute("com.epam.course.taglib.json.escapeXml.currentValue", Boolean.valueOf(pEscapeXml), 2);
	}

	public Boolean getEscapeXmlDefault() {
		return Boolean.valueOf(getBooleanDefaultValue("com.epam.course.taglib.json.escapeXml", Boolean.valueOf(true)));
	}

	protected void resetEscapeXmlValue() {
		if (mEscapeXmlValueSet) {
			getJspContext().setAttribute("com.epam.course.taglib.json.escapeXml.currentValue", mEscapeXmlOriginalValue, 2);
		}
	}

	protected Stack getEntityStack() throws JspException {
		Stack stack = (Stack) getJspContext().getAttribute("com.epam.course.taglib.json.objectStack", 2);
		if (stack == null) {
			stack = createEntityStack();
			mNewStackCreated = true;
		}
		return stack;
	}

	protected Stack createEntityStack() throws JspException {
		Stack stack = new Stack();
		getJspContext().setAttribute("com.epam.course.taglib.json.objectStack", stack, 2);
		return stack;
	}

	protected void removeEntityStack() {
		getJspContext().removeAttribute("com.epam.course.taglib.json.objectStack", 2);
	}

	protected JsonEntity getCurrentEntity() throws JspException {
		return (JsonEntity) getEntityStack().peek();
	}

	protected boolean entityStackExists() {
		return getJspContext().getAttribute("com.epam.course.taglib.json.objectStack", 2) != null;
	}

	protected boolean isRootTag() {
		return mNewStackCreated;
	}

	protected Object getDefaultValue(String pParamKey, Object pDefaultConstant) {
		Object value = getJspContext().findAttribute(pParamKey);
		if (value != null) {
			return value;
		}

		ServletContext servletContext = ((PageContext) getJspContext()).getServletContext();
		value = servletContext.getInitParameter(pParamKey);
		if (value != null) {
			return value;
		}

		return pDefaultConstant;
	}

	private boolean getBooleanDefaultValue(String pParamKey, Object pDefaultConstant) {
		Object defaultValue = getDefaultValue(pParamKey, pDefaultConstant);
		if ((defaultValue instanceof String)) {
			return Boolean.valueOf((String) defaultValue).booleanValue();
		}
		if ((defaultValue instanceof Boolean)) {
			return ((Boolean) defaultValue).booleanValue();
		}

		return false;
	}

	protected Object trimAndEscapeValue(Object pValue) {
		if (!(pValue instanceof String)) {
			return pValue;
		}

		String result = (String) pValue;

		if (getTrim()) {
			result = result.trim();
		}

		if (getEscapeXml()) {
			result = result.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
					.replaceAll("'", "&#039;").replaceAll("\"", "&#034;");
		}

		return result;
	}

	protected void processTagEnd(JsonEntity pNewEntity) throws JspException, JSONException {
		if (isRootTag()) {
			try {
				JspWriter out = getJspContext().getOut();

				String jsonText = getPrettyPrintIndentFactor() > 0 ? pNewEntity.toString(getPrettyPrintIndentFactor())
						: pNewEntity.toString();

				out.write(jsonText);
			} catch (Exception e) {
				throw new JspException(Messages.getString("com.epam.course.taglib.json.error.base.0"), e);
			} finally {
				removeEntityStack();
			}

		} else {
			getCurrentEntity().add(pNewEntity.getWrappedObject(), getName());
		}

		resetEscapeXmlValue();
	}
}
