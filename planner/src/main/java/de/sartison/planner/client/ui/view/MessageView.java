package de.sartison.planner.client.ui.view;

import de.sartison.planner.client.ui.presenter.MessagePresenter;
import de.sartison.planner.model.MessageIcon;

public interface MessageView extends View<MessagePresenter> {
	public void showMessage(MessageIcon icon, String title, String message);

	public void showMessage(String title, Throwable caught);
	
	public void showMessage(Throwable caught);

	public void clear();

	void setMessageImageUrl(String image);

	void setMessageImageVisible(boolean visible);

	void setMessageText(String message);
	
	void setMessageDesc(String description);

	void setMessageTextStyleName(String style);
	
	void setMessageDescStyleName(String style);
}
