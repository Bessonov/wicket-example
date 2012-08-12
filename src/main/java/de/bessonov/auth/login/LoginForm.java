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

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.bessonov.auth.dao.UserDao;
import de.bessonov.auth.dao.UserModel;
import de.bessonov.page.BaseForm;

public class LoginForm extends BaseForm<UserModel> {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserDao userDao;

	public LoginForm(String id, IModel<UserModel> model) {
		super(id, model);
	}

	public LoginForm(String id, UserModel userModel) {
		this(id, new CompoundPropertyModel<UserModel>(userModel));
	}

	@Override
	public void onSubmit() {
		UserModel user = userDao.findByEmail(
				getModelObject().getEmail());

		// check if user can login and do login
		if(user != null
				&& user.isPassword(getModelObject().getPassword())
				&& user.hasRole("is_user")) {

			// login user
			getSession().setUser(user);

			// goto home page
			setResponsePage(getApp().getHomePage());
			return;
		}

		transError("Wrong user data.");
	}
}
