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
package de.bessonov.auth.register;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;

import de.bessonov.BaseTest;
import de.bessonov.auth.register.RegisterPage;
import de.bessonov.page.HomePage;

public class TestRegisterPage extends BaseTest {

	// set register link path
	private final String registerLinkPath = "anonymPanel:url_register";
	private final String formPath = "registerPanel:registerForm";

	private FormTester form;

	@Override
	@Before
	public void setUp() {
		super.setUp();

		// go to register page
		tester.startPage(RegisterPage.class);

		// get register page? 
		tester.assertRenderedPage(RegisterPage.class);

		// no errors, no infos
		tester.assertNoErrorMessage();
		tester.assertNoInfoMessage();
		
		// get form
		form = tester.newFormTester(formPath);
	}

	@Test
	public void testRegisterLink() {

		// check register link on start page
		tester.startPage(HomePage.class);
		tester.assertBookmarkablePageLink(registerLinkPath,
				RegisterPage.class, new PageParameters());

		// click to go to register page
		tester.clickLink(registerLinkPath);

		// get register page? 
		tester.assertRenderedPage(RegisterPage.class);
	}

	@Test
	public void testRegisterUserFailWithoutData() {

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
	public void testRegisterUserFailInvalideData() {

		// submit form with invalid e-mail and too short password
		form.setValue("email", "invalideEmail");
		form.setValue("password", "1");
		form.submit();

		// check errors
		tester.assertErrorMessages(new String[]{
			"'invalideEmail' ist keine gültige Email-Adresse.",
			"Feld 'Passwort' muss mindestens 3 Zeichen lang sein."
		});
		tester.assertNoInfoMessage();
	}

	@Test
	public void testRegisterUserFailDoubleUser() {

		// insert user in db
		userDao.save(user);
		
		// try to submit with existing data 
		form.setValue("email", user.getEmail());
		form.setValue("password", user.getPassword());
		form.submit();

		// check errors
		tester.assertErrorMessages(new String[]{
			"Der Benutzer test@test.test existiert bereits.",
		});
		tester.assertNoInfoMessage();
	}

	@Test
	public void testRegisterUserSuccessful() {

		// register user
		form.setValue("email", user.getEmail());
		form.setValue("password", user.getPassword());
		form.submit();

		// check infos
		tester.assertNoErrorMessage();
		tester.assertInfoMessages(new String[]{
			"Sie wurden erfolgreich registriert. Sie können sich jetzt anmelden."
		});
	}
}
