package de.sartison.planner.client.ui.binder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import de.sartison.planner.client.ui.presenter.MessagePresenter;
import de.sartison.planner.client.ui.view.MessageView;
import de.sartison.planner.model.MessageIcon;

public class MessagePanel extends Composite implements MessageView {

	private MessagePresenter presenter;
	
	private static MessagePanelUiBinder uiBinder = GWT.create(MessagePanelUiBinder.class);

	interface MessagePanelUiBinder extends UiBinder<Widget, MessagePanel> {
	}

	@UiField
	Image messageImage;

	@UiField
	Label messageText;

	@UiField
	Label messageDesc;
	
	public MessagePanel() {
		initWidget(uiBinder.createAndBindUi(this));

		messageImage.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.switchSize();
			}
		});
	}

	public void showMessage(MessageIcon icon, String title, String message) {
		presenter.showMessage(icon, title, message);
	}

	public void showMessage(String title, Throwable caught) {
		presenter.showMessage(title, caught);
	}

	public void showMessage(Throwable caught) {
		presenter.showMessage(caught.getMessage(), caught);
	}

	public void clear() {
		presenter.showMessage(MessageIcon.NONE, "", "");
	}

	@Override
	public void setPresenter(MessagePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public MessagePresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void setMessageImageUrl(String image) {
		messageImage.setUrl(image);
	}

	@Override
	public void setMessageImageVisible(boolean visible) {
		messageImage.setVisible(visible);
	}

	@Override
	public void setMessageText(String message) {
		messageText.setText(message);
	}

	@Override
	public void setMessageDesc(String description) {
		messageDesc.setText(description);
	}

	@Override
	public void setMessageTextStyleName(String style) {
		messageText.setStyleName(style);
	}

	@Override
	public void setMessageDescStyleName(String style) {
		messageDesc.setStyleName(style);
	}
}