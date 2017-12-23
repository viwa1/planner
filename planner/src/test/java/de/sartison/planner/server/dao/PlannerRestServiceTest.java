package de.sartison.planner.server.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.sartison.planner.model.User;
import de.sartison.planner.server.rest.PlannerRestService;
import de.sartison.planner.server.util.Utils;

public class PlannerRestServiceTest {

	private PlannerRestService plannerRestService;

	@Before
	public void init() {
		plannerRestService = new PlannerRestService();
	}

	@Test
	public void getSalt() throws Exception {
		String salt = plannerRestService.getResourcLoginResource().getSalt("viktor");

		System.out.println(salt);

		Assert.assertNotNull(salt);
	}

	@Test
	public void authenticate() throws Exception {
		String user = "viktor";
		String password = "viktor";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getResourcLoginResource().getSalt(user);
		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		User userObj = plannerRestService.getResourcLoginResource().authenticate(new User(user, saltedPwdHash));

		Assert.assertNotNull(userObj);
		Assert.assertTrue(userObj.getId() > 0);
		Assert.assertNotNull(userObj.getName());
		Assert.assertNull(userObj.getPwd());
	}

	@Test
	public void notAuthenticateName() throws Exception {
		String user = "vikto";
		String password = "viktor";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getResourcLoginResource().getSalt(user);
		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		try {
			plannerRestService.getResourcLoginResource().authenticate(new User(user, saltedPwdHash));
			Assert.fail("Authentication succsessful");
		} catch (Exception exc) {
			//
		}
	}

	@Test
	public void notAuthenticatePassword() throws Exception {
		String user = "viktor";
		String password = "vikto";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getResourcLoginResource().getSalt(user);
		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		try {
			plannerRestService.getResourcLoginResource().authenticate(new User(user, saltedPwdHash));
			Assert.fail("Authentication succsessful");
		} catch (Exception exc) {
			//
		}
	}

	@Test
	public void notAuthenticateNameAndPassword() throws Exception {
		String user = "vikto";
		String password = "vikto";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getResourcLoginResource().getSalt(user);
		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		try {
			plannerRestService.getResourcLoginResource().authenticate(new User(user, saltedPwdHash));
			Assert.fail("Authentication succsessful");
		} catch (Exception exc) {
			//
		}
	}

	@Test
	public void authenticateSaltExpired() throws Exception {
		String user = "viktor";
		String password = "viktor";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getResourcLoginResource().getSalt(user);

		Thread.sleep(6000);

		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		try {
			plannerRestService.getResourcLoginResource().authenticate(new User(user, saltedPwdHash));
			Assert.fail("Authentication succsessful");
		} catch (Exception exc) {
			//
		}
	}
}