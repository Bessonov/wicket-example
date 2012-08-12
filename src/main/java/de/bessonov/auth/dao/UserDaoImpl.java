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

import org.hibernate.criterion.Restrictions;

import de.bessonov.wicket.dao.AbstractHibernateDaoImpl;

public class UserDaoImpl
		extends AbstractHibernateDaoImpl<UserModel>
		implements UserDao {

	public UserDaoImpl() {
		super(UserModel.class);
	}

	@Override
	public UserModel findByEmail(String email) {
		return getCriteriaUnique(Restrictions.eq(UserModel.EMAIL, email));
	}
}
