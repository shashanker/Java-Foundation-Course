package com.epam.course.taglib.json.util;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class JSONArray implements List, RandomAccess, Cloneable, Serializable {
	private static final long serialVersionUID = 7714328354182154375L;
	private ArrayList myArrayList;

	public JSONArray() {
		myArrayList = new ArrayList();
	}

	public JSONArray(JSONTokener x) throws JSONException {
		this();
		if (x.nextClean() != '[') {
			throw x.syntaxError("A JSONArray text must start with '['");
		}
		if (x.nextClean() == ']') {
			return;
		}
		x.back();
		for (;;) {
			if (x.nextClean() == ',') {
				x.back();
				myArrayList.add(null);
			} else {
				x.back();
				myArrayList.add(x.nextValue());
			}
			switch (x.nextClean()) {
			case ',':
			case ';':
				if (x.nextClean() == ']') {
					return;
				}
				x.back();
			}
		}

		//throw x.syntaxError("Expected a ',' or ']'");
	}

	public JSONArray(String string) throws JSONException {
		this(new JSONTokener(string));
	}

	public JSONArray(Collection collection) {
		myArrayList = new ArrayList(collection);
	}

	public JSONArray(Object[] array) {
		myArrayList = new ArrayList(Arrays.asList(array));
	}

	public Object get(int index) {
		Object o = opt(index);
		if (o == null) {
			throw new IndexOutOfBoundsException("JSONArray[" + index + "] not found.");
		}
		return o;
	}

	public boolean getBoolean(int index) throws JSONException {
		Object o = get(index);
		if ((o.equals(Boolean.FALSE)) || (((o instanceof String)) && (((String) o).equalsIgnoreCase("false")))) {

			return false;
		}
		if ((o.equals(Boolean.TRUE)) || (((o instanceof String)) && (((String) o).equalsIgnoreCase("true")))) {

			return true;
		}
		throw new JSONException("JSONArray[" + index + "] is not a Boolean.");
	}

	public double getDouble(int index) throws JSONException {
		Object o = get(index);
		try {
			return (o instanceof Number) ? ((Number) o).doubleValue() : Double.parseDouble((String) o);
		} catch (Exception e) {
			throw new JSONException("JSONArray[" + index + "] is not a number.");
		}
	}

	public int getInt(int index) throws JSONException {
		Object o = get(index);
		return (o instanceof Number) ? ((Number) o).intValue() : (int) getDouble(index);
	}

	public JSONArray getJSONArray(int index) throws JSONException {
		Object o = get(index);
		if ((o instanceof JSONArray)) {
			return (JSONArray) o;
		}
		throw new JSONException("JSONArray[" + index + "] is not a JSONArray.");
	}

	public JSONObject getJSONObject(int index) throws JSONException {
		Object o = get(index);
		if ((o instanceof JSONObject)) {
			return (JSONObject) o;
		}
		throw new JSONException("JSONArray[" + index + "] is not a JSONObject.");
	}

	public long getLong(int index) throws JSONException {
		Object o = get(index);
		return (long) ((o instanceof Number) ? ((Number) o).longValue() : getDouble(index));
	}

	public String getString(int index) throws JSONException {
		return get(index).toString();
	}

	public boolean isNull(int index) {
		//return JSONObject.NULL.equals(opt(index));
		return opt(index).equals(null);
	}

	public String join(String separator) throws JSONException {
		int len = length();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len; i++) {
			if (i > 0) {
				sb.append(separator);
			}
			sb.append(JSONObject.valueToString(myArrayList.get(i)));
		}
		return sb.toString();
	}

	public int length() {
		return size();
	}

	public Object opt(int index) {
		return (index < 0) || (index >= length()) ? null : myArrayList.get(index);
	}

	public boolean optBoolean(int index) {
		return optBoolean(index, false);
	}

	public boolean optBoolean(int index, boolean defaultValue) {
		try {
			return getBoolean(index);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	public double optDouble(int index)
  {
    return optDouble(index, 0D);
  }

	public double optDouble(int index, double defaultValue) {
		try {
			return getDouble(index);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	public int optInt(int index) {
		return optInt(index, 0);
	}

	public int optInt(int index, int defaultValue) {
		try {
			return getInt(index);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	public JSONArray optJSONArray(int index) {
		Object o = opt(index);
		return (o instanceof JSONArray) ? (JSONArray) o : null;
	}

	public JSONObject optJSONObject(int index) {
		Object o = opt(index);
		return (o instanceof JSONObject) ? (JSONObject) o : null;
	}

	public long optLong(int index) {
		return optLong(index, 0L);
	}

	public long optLong(int index, long defaultValue) {
		try {
			return getLong(index);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	public String optString(int index) {
		return optString(index, "");
	}

	public String optString(int index, String defaultValue) {
		Object o = opt(index);
		return o != null ? o.toString() : defaultValue;
	}

	public JSONArray put(boolean value) {
		put(value ? Boolean.TRUE : Boolean.FALSE);
		return this;
	}

	public JSONArray put(double value) throws JSONException {
		Double d = new Double(value);
		JSONObject.testValidity(d);
		put(d);
		return this;
	}

	public JSONArray put(int value) {
		put(new Integer(value));
		return this;
	}

	public JSONArray put(long value) {
		put(new Long(value));
		return this;
	}

	public JSONArray put(Object value) {
		add(value);
		return this;
	}

	public JSONArray put(int index, boolean value) throws JSONException {
		put(index, Boolean.valueOf(value));
		return this;
	}

	public JSONArray put(int index, double value) throws JSONException {
		put(index, new Double(value));
		return this;
	}

	public JSONArray put(int index, int value) throws JSONException {
		put(index, new Integer(value));
		return this;
	}

	public JSONArray put(int index, long value) throws JSONException {
		put(index, new Long(value));
		return this;
	}

	public JSONArray put(int index, Object value) throws JSONException {
		JSONObject.testValidity(value);
		if (index < 0) {
			throw new JSONException("JSONArray[" + index + "] not found.");
		}
		if (index < length()) {
			myArrayList.set(index, value);
		} else {
			while (index != length()) {
				put(null);
			}
			put(value);
		}
		return this;
	}

	public JSONObject toJSONObject(JSONArray names) throws JSONException {
		if ((names == null) || (names.length() == 0) || (length() == 0)) {
			return null;
		}
		JSONObject jo = new JSONObject();
		for (int i = 0; i < names.length(); i++) {
			jo.put(names.getString(i), opt(i));
		}
		return jo;
	}

	public String toString() {
		try {
			return '[' + join(",") + ']';
		} catch (Exception e) {
		}
		return null;
	}

	public String toString(int indentFactor) throws JSONException {
		return toString(indentFactor, 0);
	}

	String toString(int indentFactor, int indent) throws JSONException {
		int len = length();
		if (len == 0) {
			return "[]";
		}

		StringBuffer sb = new StringBuffer("[");
		if (len == 1) {
			sb.append(JSONObject.valueToString(myArrayList.get(0), indentFactor, indent));
		} else {
			int newindent = indent + indentFactor;
			sb.append('\n');
			for (int i = 0; i < len; i++) {
				if (i > 0) {
					sb.append(",\n");
				}
				for (int j = 0; j < newindent; j++) {
					sb.append(' ');
				}
				sb.append(JSONObject.valueToString(myArrayList.get(i), indentFactor, newindent));
			}

			sb.append('\n');
			for (int i = 0; i < indent; i++) {
				sb.append(' ');
			}
		}
		sb.append(']');
		return sb.toString();
	}

	public Writer write(Writer writer) throws JSONException {
		try {
			boolean b = false;
			int len = length();

			writer.write(91);

			for (int i = 0; i < len; i++) {
				if (b) {
					writer.write(44);
				}
				Object v = myArrayList.get(i);
				if ((v instanceof JSONObject)) {
					((JSONObject) v).write(writer);
				} else if ((v instanceof JSONArray)) {
					((JSONArray) v).write(writer);
				} else {
					writer.write(JSONObject.valueToString(v));
				}
				b = true;
			}
			writer.write(93);
			return writer;
		} catch (IOException e) {
			throw new JSONException(e);
		}
	}

	public boolean add(Object o) {
		return myArrayList.add(o);
	}

	public void add(int index, Object element) {
		myArrayList.add(index, element);
	}

	public boolean addAll(Collection c) {
		return myArrayList.add(c);
	}

	public boolean addAll(int index, Collection c) {
		return myArrayList.addAll(index, c);
	}

	public void clear() {
		myArrayList.clear();
	}

	public boolean contains(Object o) {
		return myArrayList.contains(o);
	}

	public boolean containsAll(Collection c) {
		return myArrayList.containsAll(c);
	}

	public int indexOf(Object o) {
		return myArrayList.indexOf(o);
	}

	public boolean isEmpty() {
		return myArrayList.isEmpty();
	}

	public Iterator iterator() {
		return myArrayList.iterator();
	}

	public int lastIndexOf(Object o) {
		return myArrayList.lastIndexOf(o);
	}

	public ListIterator listIterator() {
		return myArrayList.listIterator();
	}

	public ListIterator listIterator(int index) {
		return myArrayList.listIterator(index);
	}

	public boolean remove(Object o) {
		return myArrayList.remove(o);
	}

	public Object remove(int index) {
		return myArrayList.remove(index);
	}

	public boolean removeAll(Collection c) {
		return myArrayList.removeAll(c);
	}

	public boolean retainAll(Collection c) {
		return myArrayList.retainAll(c);
	}

	public Object set(int index, Object element) {
		return myArrayList.set(index, element);
	}

	public int size() {
		return myArrayList.size();
	}

	public List subList(int fromIndex, int toIndex) {
		return myArrayList.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return myArrayList.toArray();
	}

	public Object[] toArray(Object[] a) {
		return myArrayList.toArray(a);
	}

	public Object clone() {
		try {
			JSONArray v = (JSONArray) super.clone();
			myArrayList = ((ArrayList) myArrayList.clone());
			return v;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		Object[] array1 = toArray();
		Object[] array2 = ((JSONArray) object).toArray();
		return Arrays.equals(array1, array2);
	}

	public int hashCode() {
		return toArray().hashCode();
	}
}
