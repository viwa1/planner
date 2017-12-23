package de.sartison.planner.client.ui.presenter;

import de.sartison.planner.client.ui.view.CalendarView;

public class CalendarPresenter extends NavigablePresenter<CalendarView> {
	public CalendarPresenter(CalendarView view) {
		super(view);
	}

	@Override
	public void bind() {
		view().setPresenter(this);
	}
}
