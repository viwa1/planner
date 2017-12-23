package de.sartison.planner.client.ui.binder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import de.sartison.planner.client.ui.presenter.CalendarPresenter;
import de.sartison.planner.client.ui.view.CalendarView;

public class CalendarBinder extends PresenterComposite<CalendarPresenter> implements CalendarView {

	private static CalendarBinderUiBinder uiBinder = GWT.create(CalendarBinderUiBinder.class);

	interface CalendarBinderUiBinder extends UiBinder<Widget, CalendarBinder> {
	}

	public CalendarBinder(RootPanel rootPanel) {
		super(rootPanel);
		initWidget(uiBinder.createAndBindUi(this));
	}

}
