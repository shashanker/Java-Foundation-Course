package com.epam.course.taglib.json;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

import com.epam.course.taglib.json.util.JSONArray;

public class JsonArrayTag extends JsonBaseTag {
	protected String mVar;
	protected Object mItems;
	protected Collection mItemsCollection;

	public JsonArrayTag() {
	}

	public String getVar() {
		return mVar;
	}

	public void setVar(String pVar) {
		mVar = pVar;
	}

	public Object getItems() {
		return mItems;
	}

	public void setItems(Object pItems) {
		mItems = pItems;
		mItemsPropertySet = true;
	}

	protected boolean mItemsPropertySet = false;

	public void doTag() throws JspException {
		JspFragment body = getJspBody();

		if ((mItemsPropertySet) && (getVar() == null) && (body != null)) {
			throw new JspException(Messages.getString("com.epam.course.taglib.json.error.array.1"));
		}

		try {
			JSONArray array = new JSONArray();
			JsonEntity entity = new JsonEntity(array);
			getEntityStack().push(entity);

			if (mItemsPropertySet) {
				coerceItemsToCollection();

				if (!mItemsCollection.isEmpty()) {
					iterateOverItems(array);

				}

			} else if (body != null) {
				StringWriter writer = new StringWriter();
				body.invoke(writer);
			}

			getEntityStack().pop();

			processTagEnd(entity);

		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException(Messages.getString("com.epam.course.taglib.json.error.array.0"), e);
		}
	}

	private void iterateOverItems(JSONArray pArray) throws JspException, IOException {
		JspFragment body = getJspBody();

		Iterator it = mItemsCollection.iterator();
		while (it.hasNext()) {
			Object currentItem = it.next();

			if (body == null) {

				pArray.add(currentItem);

			} else {

				getJspContext().setAttribute(getVar(), currentItem);
				int arraySizeBeforeInvokingBody = pArray.size();

				StringWriter writer = new StringWriter();
				body.invoke(writer);

				if (pArray.size() == arraySizeBeforeInvokingBody) {

					Object value = writer.toString();

					value = trimAndEscapeValue(value);

					pArray.add(value);
				}

				getJspContext().removeAttribute(getVar());
			}
		}
	}

	private void coerceItemsToCollection() throws JspException {
		Object o = getItems();
		Collection result;
		if (o == null) {

			result = new ArrayList();
		} else {
			if (((o instanceof boolean[])) || ((o instanceof byte[])) || ((o instanceof char[]))
					|| ((o instanceof short[])) || ((o instanceof int[])) || ((o instanceof long[]))
					|| ((o instanceof float[])) || ((o instanceof double[]))) {

				result = convertArrayToList(o);
			} else {
				if ((o instanceof Object[])) {
					result = Arrays.asList((Object[]) o);
				} else {
					if ((o instanceof Collection)) {
						result = (Collection) o;
					} else {
						if ((o instanceof Map)) {
							result = ((Map) o).values();
						} else {
							if ((o instanceof String)) {
								result = Arrays.asList(((String) o).split(","));
							} else {
								String msg = MessageFormat.format(Messages.getString("com.epam.course.taglib.json.error.array.2"),
										new Object[] { o.getClass() });

								throw new JspException(msg);
							}
						}
					}
				}
			}
		}
		mItemsCollection = result;
	}

	private Collection convertArrayToList(Object pArray) {
		if (pArray == null) {
			return new ArrayList();
		}
		int length = Array.getLength(pArray);
		if (length == 0) {
			return new ArrayList();
		}
		Class wrapperType = Array.get(pArray, 0).getClass();
		Object[] newArray = (Object[]) Array.newInstance(wrapperType, length);
		for (int i = 0; i < length; i++) {
			newArray[i] = Array.get(pArray, i);
		}
		return Arrays.asList(newArray);
	}
}
