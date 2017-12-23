package de.sartison.planner.client.ui.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

import de.sartison.planner.client.ui.view.View;

public abstract class NavigablePresenter<T extends View<?>> implements Presenter {
	private T view;

	public NavigablePresenter(T view) {
		this.view = view;

		bind();
	}

	public void go(HasWidgets rootContainer) {
		rootContainer.clear();
		rootContainer.add(view.asWidget());
	}

	protected T view() {
		return view;
	}
}
