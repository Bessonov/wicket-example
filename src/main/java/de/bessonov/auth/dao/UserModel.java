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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.wicket.IClusterable;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.util.string.Strings;

import de.bessonov.wicket.dao.DomainObject;
import de.bessonov.wicket.security.IAuthModel;

@Entity
@Table(name = "user")
public class UserModel implements DomainObject, IClusterable, IAuthModel {

	private static final long serialVersionUID = 1L;

	// For criteria API and search
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";

	@Id @GeneratedValue private Long id;
	@Column(nullable=false, unique=true) private String email;
	@Column(nullable=false) private String password;

	/* utils */

	public static String cryptPass(String password) {
		// easy encrypt
		return DigestUtils.sha256Hex(password);
	}

	@Override
	public boolean equals(Object user) {
		if(getId() != null && user instanceof UserModel) {
			return getId().equals(((UserModel)user).getId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		if(getId() == null) {
			return 0;
		}
		return getId().intValue();
	}

	/* /utils */

	@Override
	public Roles getRoles() {
		// in real world get roles from DB or AS
		return new Roles("is_user");
	}

	@Override
	public boolean hasAnyRole(Roles roles) {
		return getRoles().hasAnyRole(roles);
	}

	@Override
	public boolean hasRole(String role) {
		return hasAnyRole(new Roles(role));
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = UserModel.cryptPass(password);
	}

	public String getPassword() {
		return password;
	}

	public boolean isPassword(String plainText) {
		if (Strings.isEmpty(plainText)) {
			return false;
		}
		// don't encrypt password because after
		// Model.setPassword in form already encrypted
		return password.equals(plainText);
	}
}
