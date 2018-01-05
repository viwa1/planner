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
		String salt = plannerRestService.getLoginResource().getSalt("viktor");

		System.out.println(salt);

		Assert.assertNotNull(salt);
	}

	@Test
	public void authenticate() throws Exception {
		String email = "viktor";
		String password = "viktor";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getLoginResource().getSalt(email);
		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		User userObj = plannerRestService.getLoginResource().authenticate(new User(email, saltedPwdHash));

		Assert.assertNotNull(userObj);
		Assert.assertTrue(userObj.getId() > 0);
		Assert.assertNotNull(userObj.getEmail());
		Assert.assertNull(userObj.getPwd());
	}

	@Test
	public void notAuthenticateEmail() throws Exception {
		String email = "vikto";
		String password = "viktor";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getLoginResource().getSalt(email);
		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		try {
			plannerRestService.getLoginResource().authenticate(new User(email, saltedPwdHash));
			Assert.fail("Authentication succsessful");
		} catch (Exception exc) {
			//
		}
	}

	@Test
	public void notAuthenticatePassword() throws Exception {
		String email = "viktor";
		String password = "vikto";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getLoginResource().getSalt(email);
		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		try {
			plannerRestService.getLoginResource().authenticate(new User(email, saltedPwdHash));
			Assert.fail("Authentication succsessful");
		} catch (Exception exc) {
			//
		}
	}

	@Test
	public void notAuthenticateEmailAndPassword() throws Exception {
		String email = "vikto";
		String password = "vikto";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getLoginResource().getSalt(email);
		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		try {
			plannerRestService.getLoginResource().authenticate(new User(email, saltedPwdHash));
			Assert.fail("Authentication succsessful");
		} catch (Exception exc) {
			//
		}
	}

	@Test
	public void authenticateSaltExpired() throws Exception {
		String email = "viktor";
		String password = "viktor";

		String pwdSha256 = Utils.sha256(password);
		String salt = plannerRestService.getLoginResource().getSalt(email);

		Thread.sleep(6000);

		String saltedPwdHash = Utils.sha256(salt + pwdSha256);

		try {
			plannerRestService.getLoginResource().authenticate(new User(email, saltedPwdHash));
			Assert.fail("Authentication succsessful");
		} catch (Exception exc) {
			//
		}
	}
}