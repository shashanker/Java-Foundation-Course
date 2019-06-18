package com.epam.course.taglib.json.util;

import java.io.IOException;
import java.io.Writer;

public class JSONWriter {
	private static final int maxdepth = 20;
	private boolean comma;
	protected char mode;
	private char[] stack;
	private int top;
	protected Writer writer;

	public JSONWriter(Writer w) {
		comma = false;
		mode = 'i';
		stack = new char[20];
		top = 0;
		writer = w;
	}

	private JSONWriter append(String s) throws JSONException {
		if (s == null) {
			throw new JSONException("Null pointer");
		}
		if ((mode == 'o') || (mode == 'a')) {
			try {
				if ((comma) && (mode == 'a')) {
					writer.write(44);
				}
				writer.write(s);
			} catch (IOException e) {
				throw new JSONException(e);
			}
			if (mode == 'o') {
				mode = 'k';
			}
			comma = true;
			return this;
		}
		throw new JSONException("Value out of sequence.");
	}

	public JSONWriter array() throws JSONException {
		if ((mode == 'i') || (mode == 'o') || (mode == 'a')) {
			push('a');
			append("[");
			comma = false;
			return this;
		}
		throw new JSONException("Misplaced array.");
	}

	private JSONWriter end(char m, char c) throws JSONException {
		if (mode != m) {
			throw new JSONException(m == 'o' ? "Misplaced endObject." : "Misplaced endArray.");
		}

		pop(m);
		try {
			writer.write(c);
		} catch (IOException e) {
			throw new JSONException(e);
		}
		comma = true;
		return this;
	}

	public JSONWriter endArray() throws JSONException {
		return end('a', ']');
	}

	public JSONWriter endObject() throws JSONException {
		return end('k', '}');
	}

	public JSONWriter key(String s) throws JSONException {
		if (s == null) {
			throw new JSONException("Null key.");
		}
		if (mode == 'k') {
			try {
				if (comma) {
					writer.write(44);
				}
				writer.write(JSONObject.quote(s));
				writer.write(58);
				comma = false;
				mode = 'o';
				return this;
			} catch (IOException e) {
				throw new JSONException(e);
			}
		}
		throw new JSONException("Misplaced key.");
	}

	public JSONWriter object() throws JSONException {
		if (mode == 'i') {
			mode = 'o';
		}
		if ((mode == 'o') || (mode == 'a')) {
			append("{");
			push('k');
			comma = false;
			return this;
		}
		throw new JSONException("Misplaced object.");
	}

	private void pop(char c) throws JSONException {
		if ((top <= 0) || (stack[(top - 1)] != c)) {
			throw new JSONException("Nesting error.");
		}
		top -= 1;
		mode = (top == 0 ? 'd' : stack[(top - 1)]);
	}

	private void push(char c) throws JSONException {
		if (top >= 20) {
			throw new JSONException("Nesting too deep.");
		}
		stack[top] = c;
		mode = c;
		top += 1;
	}

	public JSONWriter value(boolean b) throws JSONException {
		return append(b ? "true" : "false");
	}

	public JSONWriter value(double d) throws JSONException {
		return value(new Double(d));
	}

	public JSONWriter value(long l) throws JSONException {
		return append(Long.toString(l));
	}

	public JSONWriter value(Object o) throws JSONException {
		return append(JSONObject.valueToString(o));
	}
}
