package com.epam.course.taglib.json.util;

import java.io.StringWriter;

public class JSONStringer extends JSONWriter {
	public JSONStringer() {
		super(new StringWriter());
	}

	public String toString() {
		return mode == 'd' ? writer.toString() : null;
	}
}
