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
package de.bessonov.wicket.security;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

public class UserRolesAuthorizer implements IRoleCheckingStrategy {
	@Override
	public boolean hasAnyRole(Roles roles) {
		IUserSession<?> authSession = (IUserSession<?>)Session.get();
		IAuthModel user = authSession.getUser();
		return user.hasAnyRole(roles);
	}

}
