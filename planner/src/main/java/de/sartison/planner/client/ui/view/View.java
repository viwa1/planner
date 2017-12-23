package de.sartison.planner.client.ui.view;

import com.google.gwt.user.client.ui.IsWidget;

import de.sartison.planner.client.ui.presenter.Presenter;

public interface View<T extends Presenter> extends IsWidget {
	void setPresenter(T presenter);

	T getPresenter();
}
