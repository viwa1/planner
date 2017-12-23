package de.sartison.planner.client.ui.view;

import de.sartison.planner.client.ui.binder.RootPanel;
import de.sartison.planner.client.ui.presenter.Presenter;

public interface NavigableView<T extends Presenter> extends View<T> {
	RootPanel getRootPanel();
}
