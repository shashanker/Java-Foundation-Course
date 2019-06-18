package com.epam.course.taglib.json.util;

public class JSONException extends Exception {
	private static final long serialVersionUID = -8568311320601213161L;

	private Throwable cause;

	public JSONException(String message) {
		super(message);
	}

	public JSONException(Throwable t) {
		super(t.getMessage());
		cause = t;
	}

	public Throwable getCause() {
		return cause;
	}
}
