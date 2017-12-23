package de.sartison.planner.client.ui.binder;

import com.google.gwt.user.client.ui.Composite;

import de.sartison.planner.client.ui.presenter.Presenter;

public class PresenterComposite<T extends Presenter> extends Composite {
	private T presenter;
	private RootPanel rootPanel;

	public PresenterComposite(RootPanel rootPanel) {
		this.rootPanel = rootPanel;
	}

	public void setPresenter(T presenter) {
		this.presenter = presenter;
	}

	public T getPresenter() {
		return presenter;
	}

	public RootPanel getRootPanel() {
		return rootPanel;
	}
}
