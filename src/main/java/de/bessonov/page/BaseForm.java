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
package de.bessonov.page;

import static de.bessonov.wicket.MessageUtils._;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import de.bessonov.WicketApplication;
import de.bessonov.auth.dao.UserModel;
import de.bessonov.wicket.security.UserSession;

public abstract class BaseForm<T> extends Form<T>  {

	private static final long serialVersionUID = 1L;

	public BaseForm(String id) {
		super(id);
	}

	public BaseForm(String id, IModel<T> model) {
		super(id, model);
	}

	/**
	 * Get application specific UserSession
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UserSession<UserModel> getSession() {
		return (UserSession<UserModel>) Session.get();
	}

	/**
	 * Get application specific WebApplication 
	 */
	public WicketApplication getApp() {
		return (WicketApplication) getApplication();
	}

	/**
	 * Translate and post error message in form
	 * 
	 * @param message
	 */
	public void transError(String message) {
		error(_(message).getString());
	}

	/**
	 * Translate and flash info message
	 *  
	 * @param message
	 */
	public void flashInfo(String message) {
		getSession().info(_(message).getString());
	}
}
