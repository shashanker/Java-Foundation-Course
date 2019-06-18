package com.epam.course.taglib.json;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "com.epam.course.taglib.json.messages";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("com.epam.course.taglib.json.messages");

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
		}
		return '!' + key + '!';
	}
}
