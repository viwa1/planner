package de.sartison.planner.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface I18N extends Messages {
	public final static I18N INSTANCE = GWT.create(I18N.class);
	
	//Labels
	String emailLabel();
	String passwordLabel();
	String sendLabel();
	String wellcomeLabel();
	String wellcomeLabelDesc();
	
	//Info
	String remainSignedInInfo();
	String authenticationStarted();
	String authenticationStartedDesc();
	
	//UI Errors
	String authenticationFailedException();
	String userEmailFormatExceptionDesc();
	String passwordFormatExceptionDesc(int minLength);
	String loginInputFormatException();
}
