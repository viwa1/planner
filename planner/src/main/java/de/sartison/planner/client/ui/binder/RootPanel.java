package de.sartison.planner.client.ui.binder;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import de.sartison.planner.client.ui.presenter.CalendarPresenter;
import de.sartison.planner.client.ui.presenter.LoginPresenter;
import de.sartison.planner.client.ui.presenter.MessagePresenter;
import de.sartison.planner.client.ui.presenter.NavigablePresenter;
import de.sartison.planner.client.ui.view.MessageView;
import de.sartison.planner.client.ui.view.View;

public class RootPanel extends Composite implements EntryPoint {

	private static RootPanelUiBinder uiBinder = GWT.create(RootPanelUiBinder.class);
	private final static Map<Class<? extends NavigablePresenter<? extends View<?>>>, NavigablePresenter<? extends View<?>>> presenters = new HashMap<>();

	private LoginBinder loginBinder;
	private CalendarBinder calendarBinder;

	@UiField(provided = true)
	protected MessagePanel messagePanel = new MessagePanel();

	@UiField
	protected FlowPanel root;

	@UiField
	protected HTMLPanel effect;

	interface RootPanelUiBinder extends UiBinder<Widget, RootPanel> {
	}

	public RootPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		loginBinder = new LoginBinder(this);
		calendarBinder = new CalendarBinder(this);

		new MessagePresenter(messagePanel);

		presenters.put(LoginPresenter.class, new LoginPresenter(loginBinder));
		presenters.put(CalendarPresenter.class, new CalendarPresenter(calendarBinder));
	}

	public void go(Class<? extends NavigablePresenter<? extends View<?>>> clazz) {
		NavigablePresenter<? extends View<?>> presenter = presenters.get(clazz);
		presenter.go(root);
		messagePanel.clear();
	}

	public MessageView getMessageView() {
		return messagePanel;
	}

	@Override
	public void onModuleLoad() {
		RootLayoutPanel.get().add(this);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		effect(false);

		loginBinder.getPresenter().go(root);
	}

	public void effect(boolean on) {
		if (on) {
			effect.setStyleName("effect-on");
		} else {
			effect.setStyleName("effect-off");
		}
	}
}