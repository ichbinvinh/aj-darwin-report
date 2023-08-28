package de.analytikjena.darwin.report;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18n {
	private static final String BUNDLE_NAME = "de.analytikjena.darwin.report.i18n"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private I18n() {}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
