package de.sartison.planner.client.dao;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.TextCallback;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.sartison.planner.client.rest.PlannerRestService;
import de.sartison.planner.client.util.Utils;
import de.sartison.planner.model.User;

public class RemoteAccess {
	
	private PlannerRestService service = GWT.create(PlannerRestService.class);

	public void login(String userEmail, String password, AsyncCallback<User> callback) {
		getSalt(userEmail, password, callback);
	}

	private void getSalt(String userEmail, String password, AsyncCallback<User> callback) {
		service.getSalt(userEmail, new TextCallback() {

			@Override
			public void onSuccess(Method method, String salt) {
				String pwdSha256 = Utils.sha256(password);
				String saltedPwdHash = Utils.sha256(salt + pwdSha256);

				authenticate(userEmail, saltedPwdHash, callback);
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				callback.onFailure(exception);
			}
		});
	}

	private void authenticate(String userEmail, String password, AsyncCallback<User> callback) {
		User userObj = new User();

		userObj.setEmail(userEmail);
		userObj.setPwd(password);

		service.authenticate(userObj, new MethodCallback<User>() {

			@Override
			public void onSuccess(Method method, User response) {
				callback.onSuccess(response);
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				callback.onFailure(exception);
			}
		});
	}
}