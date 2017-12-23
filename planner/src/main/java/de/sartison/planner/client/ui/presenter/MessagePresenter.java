package de.sartison.planner.client.ui.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;

import de.sartison.planner.client.ui.binder.MessagePanel;
import de.sartison.planner.client.ui.view.MessageView;
import de.sartison.planner.client.util.I18N;
import de.sartison.planner.client.util.Preferences;
import de.sartison.planner.model.MessageIcon;

public class MessagePresenter implements Presenter {

	private final static Logger LOGGER = Logger.getLogger(MessagePresenter.class.getName());

	private final static double MIN_HEIGHT = 33;
	private final static double MAX_HEIGHT = 66;

	private MessageView view;

	public MessagePresenter(MessageView view) {
		this.view = view;

		bind();

		MessagePanel messagePanel = (MessagePanel) view;
		messagePanel.getElement().getStyle().setHeight(MIN_HEIGHT, Unit.PX);
		
		showMessage(MessageIcon.INFO, I18N.INSTANCE.wellcomeLabel(), I18N.INSTANCE.wellcomeLabelDesc());
	}

	@Override
	public void bind() {
		this.view.setPresenter(this);
	}

	/**
	 * Show message in the MessageBox.
	 * 
	 * @param icon
	 *            which should be displayed
	 * @param message
	 *            text which should be displayed
	 */
	public void showMessage(MessageIcon icon, String title, String description) {
		String image;
		String titleStyle;
		String descStyle;

		switch (icon) {
		case ERROR:
			image = Preferences.INSTANCE.errorMessageIcon();
			titleStyle = Preferences.INSTANCE.errorMessageTitleStyle();
			descStyle = Preferences.INSTANCE.errorMessageDescStyle();
			break;

		case WARNING:
			image = Preferences.INSTANCE.warningMessageIcon();
			titleStyle = Preferences.INSTANCE.warningMessageTitleStyle();
			descStyle = Preferences.INSTANCE.warningMessageDescStyle();
			break;

		case INFO:
			image = Preferences.INSTANCE.infoMessageIcon();
			titleStyle = Preferences.INSTANCE.infoMessageTitleStyle();
			descStyle = Preferences.INSTANCE.infoMessageDescStyle();
			break;

		default:
			image = Preferences.INSTANCE.defaultMessageIcon();
			titleStyle = Preferences.INSTANCE.defaultMessageTitleStyle();
			descStyle = Preferences.INSTANCE.defaultMessageDescStyle();
			break;
		}

		view.setMessageImageUrl("pics/32x32/" + image);
		view.setMessageImageVisible(!titleStyle.equals(Preferences.INSTANCE.defaultMessageTitleStyle()));

		view.setMessageText(title);
		view.setMessageDesc(description);
		
		view.setMessageTextStyleName(titleStyle);
		view.setMessageDescStyleName(descStyle);
	}

	public void showMessage(String title, Throwable caught) {
		LOGGER.log(Level.SEVERE, title, caught);
		showMessage(MessageIcon.ERROR, title, caught.getMessage());
	}

	public void switchSize() {
		MessagePanel messagePanel = (MessagePanel) view;
		String height = messagePanel.getElement().getStyle().getHeight();
		double curHeight;
		
		if (height.contains("" + MIN_HEIGHT)) {
			curHeight = MAX_HEIGHT;
		} else {
			curHeight = MIN_HEIGHT;
		}
		
		messagePanel.getElement().getStyle().setHeight(curHeight, Unit.PX);
	}
}