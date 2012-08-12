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

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator.MinimumLengthValidator;

import de.bessonov.auth.dao.UserDao;
import de.bessonov.auth.dao.UserModel;
import de.bessonov.wicket.validator.DbValidator.UniqueInDbValidator;

public class RegisterPanel extends Panel {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserDao userDao;

	public RegisterPanel(String id, UserModel user) {
		super(id);

		RegisterForm registerForm = new RegisterForm("registerForm", user);

		// check with regexp for valid email
		// then check if not already registered
		registerForm.add(new RequiredTextField<String>(UserModel.EMAIL)
			.add(EmailAddressValidator.getInstance())
			.add(UniqueInDbValidator.findByProperty(userDao, UserModel.EMAIL)));

		// minimum 3 chars length
		registerForm.add(new PasswordTextField(UserModel.PASSWORD)
			.add(MinimumLengthValidator.minimumLength(3)));

		add(registerForm);
	}
}
