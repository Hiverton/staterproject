package com.example.algamoney.api.database;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Banco {
	private Banco() {

	}

	public static String applicationProperties = "application";

	public static String getStringApplication(String key) {
		ResourceBundle applicationBundle = ResourceBundle.getBundle(applicationProperties);
		try {
			return applicationBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}

