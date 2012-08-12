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

import org.springframework.transaction.annotation.Transactional;

import de.bessonov.wicket.dao.Dao;

public interface UserDao extends Dao<UserModel> {

	@Transactional(readOnly = true)
	UserModel findByEmail(String email);
}
