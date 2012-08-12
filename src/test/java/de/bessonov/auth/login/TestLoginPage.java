/*******************************************************************************
 * Copyright (c) 2012 Anton Bessonov.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons
 * Attribution 3.0 License which accompanies this distribution,
 * and is available at
 * http://creativecommons.org/licenses/by/3.0/
 * 
 * Contributors:
 *     Anton Bessonov - initial API and implementation
 ******************************************************************************/
package de.bessonov.auth.login;

import static junit.framework.Assert.assertEquals;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;

import de.bessonov.BaseTest;
import de.bessonov.auth.dao.UserAnonymous;
import de.bessonov.auth.dao.UserModel;
import de.bessonov.auth.login.LoginPage;
import de.bessonov.page.HomePage;
import de.bessonov.wicket.security.UserSession;

public class TestLoginPage extends BaseTest {

	// set links and form path
	private final String loginLinkPath = "anonymPanel:url_login";
	private final String logoutLinkPath = "authPanel:url_logout";
	private final String formPath = "loginPanel:loginForm";

	private FormTester form;

	@Override
	@Before
	public void setUp() {
		super.setUp();

		// go to login page
		tester.startPage(LoginPage.class);

		// no errors, no info
		tester.assertNoErrorMessage();
		tester.assertNoInfoMessage();
		
		// get form
		form = tester.newFormTester(formPath);

		// add test user to db
		user = userDao.save(user);
	}

	@Test
	public void testLoginLink() {

		// check login link on start page
		tester.startPage(HomePage.class);
		tester.assertBookmarkablePageLink(loginLinkPath,
				LoginPage.class, new PageParameters());

		// click to go to login page
		tester.clickLink(loginLinkPath);

		// get login page? 
		tester.assertRenderedPage(LoginPage.class);
	}

	@Test
	public void testLoginUserFailWithoutData() {

		// submit form without data
		form.submit();

		// check errors
		tester.assertErrorMessages(new String[]{
			"Bitte tragen Sie einen Wert im Feld 'E-Mail' ein.",
			"Bitte tragen Sie einen Wert im Feld 'Passwort' ein."
		});
		tester.assertNoInfoMessage();
	}

	@Test
	public void testLoginUserFailInvalideData() {

		// submit form with invalid e-mail and too short password
		form.setValue("email", "invalideEmail");
		form.setValue("password", "1");
		form.submit();

		// check errors (no error for too short password)
		tester.assertErrorMessages(new String[]{
			"'invalideEmail' ist keine gültige Email-Adresse."
		});
		tester.assertNoInfoMessage();
	}

	@Test
	public void testLoginUserFailInvalideUser() {

		// submit form with invalid e-mail
		form.setValue("email", "1" + user.getEmail());
		form.setValue("password", user.getPassword());
		form.submit();

		// check errors
		tester.assertErrorMessages(new String[]{
			"Ungültige Benutzerdaten."
		});
		tester.assertNoInfoMessage();
	}

	@Test
	public void testLoginUserSuccessful() {

		// login user
		form.setValue("email", user.getEmail());
		form.setValue("password", userPassword);
		form.submit();

		// check infos
		tester.assertNoErrorMessage();
		tester.assertNoInfoMessage();

		// check user in session
		assertEquals(user, session.getUser());

		// check logout link
		tester.assertVisible(logoutLinkPath);

		// assert rendered label component
		tester.assertLabel("userGreeting", "Guten Tag, test@test.test!");
	}

	@SuppressWarnings({ "unchecked" })
	@Test
	public void testLogoutUser() {

		// login user
		session.setUser(user);

		// go to start page 
		tester.startPage(HomePage.class);

		// check logout link
		tester.assertVisible(logoutLinkPath);

		// logout user
		tester.clickLink(logoutLinkPath, false);

		// check info
		tester.assertNoErrorMessage();
		tester.assertNoInfoMessage();

		// assert rendered page class
		tester.assertRenderedPage(HomePage.class);

		// check login link
		tester.assertVisible(loginLinkPath);

		// check user in session
		assertEquals(new UserAnonymous(),
				((UserSession<UserModel>) UserSession.get()).getUser());
	}
}
