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

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public final class UserSession<T extends IAuthModel>
		extends WebSession
		implements IUserSession<T> {

	private static final long serialVersionUID = 1L;

	// default user, ex. AnonymousUser
	final protected T defaultUser;
	// current user
	protected T user;

    public UserSession(Request request, T defaultUser) {
        super(request);

        if (defaultUser == null) {
			throw new IllegalArgumentException("Default user cannot be null");
		}

        this.defaultUser = defaultUser;
        setUser(defaultUser);
    }

    @Override
	public T getUser() {
        return user;
    }

    @Override
	public void setUser(T user) {
    	this.user = user;
    }

    @Override
    public void invalidate() {
    	super.invalidate();
    	setUser(defaultUser);
    }
}
