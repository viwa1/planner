package de.sartison.planner.client.ui.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.sartison.planner.client.dao.RemoteAccess;
import de.sartison.planner.client.ui.view.LoginView;
import de.sartison.planner.client.util.I18N;
import de.sartison.planner.client.util.Preferences;
import de.sartison.planner.client.util.Utils;
import de.sartison.planner.exception.ValidationException;
import de.sartison.planner.model.MessageIcon;
import de.sartison.planner.model.User;

public class LoginPresenter extends NavigablePresenter<LoginView> {
	private final static Logger LOGGER = Logger.getLogger(LoginPresenter.class.getName());

	private RemoteAccess remoteAccess = new RemoteAccess();

	public LoginPresenter(LoginView view) {
		super(view);
		init();
	}

	@Override
	public void bind() {
		view().setPresenter(this);
	}

	public void tryLogin() {
		String user = view().getUser().getValue();
		String password = view().getPassword().getValue();

		view().getRootPanel().getMessageView().showMessage(MessageIcon.INFO, I18N.INSTANCE.authenticationStarted(),
				I18N.INSTANCE.authenticationStartedDesc());

		try {
			validateLoginData(user, password);

			remoteAccess.login(user, password, new AsyncCallback<User>() {

				@Override
				public void onSuccess(User result) {
					LOGGER.log(Level.INFO, "User: " + result + " authenticated");
					view().getRootPanel().go(CalendarPresenter.class);
				}

				@Override
				public void onFailure(Throwable caught) {
					LOGGER.log(Level.SEVERE, "Error: " + caught);
					view().getRootPanel().getMessageView().showMessage(I18N.INSTANCE.authenticationFailedException(),
							caught);
				}
			});
		} catch (ValidationException e) {
			LOGGER.log(Level.WARNING, "Warning: " + e);
			view().getRootPanel().getMessageView().showMessage(e.getTitle(), e);
		}
	}

	private void validateLoginData(String user, String password) throws ValidationException {
		I18N instance = I18N.INSTANCE;
		Preferences preferences = Preferences.INSTANCE;

		if (Utils.isNullOrEmpty(user) || (Preferences.INSTANCE.minUsernameLength() > user.trim().length())) {
			throw new ValidationException(instance.loginInputFormatException(),
					instance.userNameFormatExceptionDesc(preferences.minUsernameLength()));
		}

		if (Utils.isNullOrEmpty(password) || (Preferences.INSTANCE.minPasswordLength() > password.trim().length())) {
			throw new ValidationException(instance.loginInputFormatException(),
					instance.passwordFormatExceptionDesc(preferences.minPasswordLength()));
		}
	}

	private void init() {
		view().getUser().getElement().setPropertyString("placeholder", I18N.INSTANCE.userLabel());
		view().getPassword().getElement().setPropertyString("placeholder", I18N.INSTANCE.passwordLabel());
	}
}
