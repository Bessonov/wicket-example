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

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import de.bessonov.auth.dao.UserModel;

public class LoginPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public LoginPanel(String id, UserModel user) {
		super(id);

		LoginForm loginForm = new LoginForm("loginForm", user);

		loginForm.add(new RequiredTextField<String>(UserModel.EMAIL)
			.add(EmailAddressValidator.getInstance()));
		loginForm.add(new PasswordTextField(UserModel.PASSWORD));

		add(loginForm);
	}
}
