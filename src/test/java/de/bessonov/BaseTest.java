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
package de.bessonov;

import java.util.Locale;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import de.bessonov.auth.dao.UserDao;
import de.bessonov.auth.dao.UserModel;
import de.bessonov.wicket.security.UserSession;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public abstract class BaseTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	protected UserDao userDao;

	// plain password for testuser
	protected final String userPassword = "test";

	protected WicketTester tester;
	protected UserSession<UserModel> session;
	protected UserModel user; 

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		// create new application and init Spring DI
		WicketApplication app = new WicketApplication() {
			@Override
			protected void initSpring() {
				// Bind Spring DI
				getComponentInstantiationListeners().add(
					new SpringComponentInjector(this, applicationContext));
			};
		};
		tester = new WicketTester(app);
		//tester.setupRequestAndResponse();
		session = (UserSession<UserModel>)tester.getSession();
		session.setLocale(Locale.GERMAN);

		// create test user
		user = new UserModel();
		user.setEmail("test@test.test");
		user.setPassword(userPassword);
	}

	@After
	public void tearDown() {
		// remove user from db if exists
		UserModel testUser = userDao.findByEmail(user.getEmail());
		if (testUser != null) {
			userDao.delete(testUser);
		}
	}
}
