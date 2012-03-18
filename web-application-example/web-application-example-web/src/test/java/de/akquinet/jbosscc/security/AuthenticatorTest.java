package de.akquinet.jbosscc.security;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Rule;
import org.junit.Test;

import de.akquinet.jbosscc.User;
import de.akquinet.jbosscc.dao.UserDao;
import de.akquinet.jbosscc.testdata.UserTestdataBuilder;
import de.akquinet.jbosscc.needle.annotation.InjectIntoMany;
import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import de.akquinet.jbosscc.needle.mock.EasyMockProvider;

public class AuthenticatorTest {

	@Rule
	public NeedleRule needleRule = new NeedleRule();

	@ObjectUnderTest
	private Authenticator authenticator;

	@Inject
	private EasyMockProvider mockProvider;

	@Inject
	private UserDao userDaoMock;

	@Inject
	private HttpSession httpSessionMock;

	@InjectIntoMany
	private Identity identity = new Identity();

	@Test
	public void testLoginFailed() throws Exception {
		EasyMock.expect(
				userDaoMock.findByUsername(EasyMock.<String> anyObject()))
				.andReturn(null);

		mockProvider.replayAll();
		boolean login = authenticator.login();
		Assert.assertFalse(identity.isLoggedIn());
		Assert.assertFalse(login);

		mockProvider.verifyAll();
	}

	@Test
	public void testLoginWithWrongPassword() throws Exception {
		String username = "username";
		EasyMock.expect(userDaoMock.findByUsername(username)).andReturn(
				new UserTestdataBuilder().withUsername(username).build());

		mockProvider.replayAll();
		authenticator.setUsername(username);
		authenticator.setPassword("any");
		boolean login = authenticator.login();
		Assert.assertFalse(login);
		Assert.assertFalse(identity.isLoggedIn());
		mockProvider.verifyAll();
	}

	@Test
	public void testLoginSuccess() throws Exception {
		String username = "username";
		User user = new UserTestdataBuilder().withUsername(username).build();
		EasyMock.expect(userDaoMock.findByUsername(username)).andReturn(user);

		mockProvider.replayAll();
		authenticator.setUsername(username);
		authenticator.setPassword("secret");
		boolean login = authenticator.login();
		Assert.assertTrue(login);
		Assert.assertTrue(identity.isLoggedIn());
		Assert.assertEquals(identity.getUser(), user);
		mockProvider.verifyAll();
	}

	@Test
	public void testLogout() throws Exception {
		mockProvider.resetToStrict(httpSessionMock);
		httpSessionMock.invalidate();

		mockProvider.replayAll();

		Assert.assertTrue(authenticator.logout());
		mockProvider.verifyAll();
	}
}
