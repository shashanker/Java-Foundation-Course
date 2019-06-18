package com.epam.course.taglib.json;

import com.epam.course.taglib.json.util.JSONArray;
import com.epam.course.taglib.json.util.JSONException;
import com.epam.course.taglib.json.util.JSONObject;

public class JsonEntity {
	private Object mWrappedObject;

	public JsonEntity(Object pWrapped) {
		mWrappedObject = pWrapped;
	}

	public void add(Object pEntity, String pName) throws JSONException {
		if ((mWrappedObject instanceof JSONObject)) {
			if (pName == null) {
				throw new JSONException("Unable to add to JSONObject - property name is required.");
			}
			((JSONObject) mWrappedObject).put(pName, pEntity);
		} else if ((mWrappedObject instanceof JSONArray)) {
			((JSONArray) mWrappedObject).add(pEntity);
		}
	}

	public Object getWrappedObject() {
		return mWrappedObject;
	}

	public boolean isArray() {
		return mWrappedObject instanceof JSONArray;
	}

	public boolean isObject() {
		return mWrappedObject instanceof JSONObject;
	}

	public String toString() {
		return mWrappedObject.toString();
	}

	public String toString(int pIndentFactor) throws JSONException {
		if (isArray()) {
			return ((JSONArray) mWrappedObject).toString(pIndentFactor);
		}
		if (isObject()) {
			return ((JSONObject) mWrappedObject).toString(pIndentFactor);
		}

		return "";
	}
}
