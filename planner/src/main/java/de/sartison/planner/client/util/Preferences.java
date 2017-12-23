package de.sartison.planner.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface Preferences extends Constants {
	public final static Preferences INSTANCE = GWT.create(Preferences.class);

	int minUsernameLength();

	int minPasswordLength();

	@DefaultStringValue("error.png")
	String errorMessageIcon();

	@DefaultStringValue("warning.png")
	String warningMessageIcon();

	@DefaultStringValue("information.png")
	String infoMessageIcon();

	@DefaultStringValue("empty.png")
	String defaultMessageIcon();

	@DefaultStringValue("messageErrorTitleStyle")
	String errorMessageTitleStyle();

	@DefaultStringValue("messageWarningTitleStyle")
	String warningMessageTitleStyle();

	@DefaultStringValue("messageInfoTitleStyle")
	String infoMessageTitleStyle();

	@DefaultStringValue("messageDefaultTitleStyle")
	String defaultMessageTitleStyle();

	@DefaultStringValue("messageErrorDescStyle")
	String errorMessageDescStyle();

	@DefaultStringValue("messageWarningDescStyle")
	String warningMessageDescStyle();

	@DefaultStringValue("messageInfoDescStyle")
	String infoMessageDescStyle();

	@DefaultStringValue("messageDefaultDescStyle")
	String defaultMessageDescStyle();
}