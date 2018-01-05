package de.sartison.planner.client.ui.view;

import com.google.gwt.user.client.ui.TextBoxBase;

import de.sartison.planner.client.ui.presenter.LoginPresenter;

public interface LoginView extends NavigableView<LoginPresenter> {
	TextBoxBase getEmail();

	TextBoxBase getPassword();
}
