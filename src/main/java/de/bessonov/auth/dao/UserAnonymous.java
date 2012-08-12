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
package de.bessonov.auth.dao;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;


public class UserAnonymous extends UserModel {
	private static final long serialVersionUID = 1L;

	@Override
	public Roles getRoles() {
		// anonymous users have only one role
		return new Roles("is_anonymous");
	}

	@Override
	public boolean equals(Object user) {
		if (user instanceof UserAnonymous) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}
}
