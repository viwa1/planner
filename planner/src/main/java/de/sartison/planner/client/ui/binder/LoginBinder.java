package de.sartison.planner.client.ui.binder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

import de.sartison.planner.client.ui.presenter.LoginPresenter;
import de.sartison.planner.client.ui.view.LoginView;

public class LoginBinder extends PresenterComposite<LoginPresenter> implements LoginView {

	private static LoginBinderUiBinder uiBinder = GWT.create(LoginBinderUiBinder.class);

	@UiField
	protected TextBox emailTextBox;

	@UiField
	protected PasswordTextBox passwordTextBox;

	interface LoginBinderUiBinder extends UiBinder<Widget, LoginBinder> {
	}

	public LoginBinder(RootPanel rootPanel) {
		super(rootPanel);
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("emailTextBox")
	public void onEmailTextBoxKeyUp(KeyUpEvent event) {
		if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			onLoginButtonClicked(null);
		}
	}

	@UiHandler("passwordTextBox")
	public void onPasswordTextBoxKeyUp(KeyUpEvent event) {
		if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			onLoginButtonClicked(null);
		}
	}
	
	@UiHandler("loginButton")
	public void onLoginButtonClicked(ClickEvent event) {
		getPresenter().tryLogin();
	}

	@Override
	public TextBoxBase getEmail() {
		return emailTextBox;
	}

	@Override
	public TextBoxBase getPassword() {
		return passwordTextBox;
	}
}