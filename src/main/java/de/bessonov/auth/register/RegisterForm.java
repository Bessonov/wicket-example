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

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.bessonov.auth.dao.UserDao;
import de.bessonov.auth.dao.UserModel;
import de.bessonov.page.BaseForm;

public class RegisterForm extends BaseForm<UserModel> {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserDao userDao;

	public RegisterForm(String id, IModel<UserModel> model) {
		super(id, model);
	}

	public RegisterForm(String id, UserModel user) {
		this(id, new CompoundPropertyModel<UserModel>(user));
	}

	@Override
	public void onSubmit() {
		UserModel user = getModelObject();
		userDao.save(user);

		flashInfo("You have successfully registered. You can login now.");

		setResponsePage(getApp().getLoginPage());
	}
}
