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
package de.bessonov.wicket.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.StringValidator;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import de.bessonov.wicket.dao.Dao;

abstract public class DbValidator extends StringValidator {
	private static final long serialVersionUID = 1L;

	/**
	 * Check if property not in DB.
	 * To change error messages use UniqueInDbValidator.findByProperty.{property}
	 * 
	 * @param dao
	 *        dao to check with
	 */
	public static class UniqueInDbValidator extends DbValidator {
		private static final long serialVersionUID = 1L;

		private Dao<?> dao;
		private String property;

		public UniqueInDbValidator(Dao<?> dao, String property) {
			this.dao = dao;
			this.property = property;
		}

		@Override
		protected String resourceKey() {
			return "UniqueInDbValidator.findByProperty." + property;
		}

		@Override
		protected void onValidate(IValidatable<String> validatable) {
			SimpleExpression expression =
					Restrictions.eq(property, validatable.getValue());
			Object user = dao.getCriteriaUnique(expression);
			if(user != null) {
				error(validatable);
			}
		}
	}

	public static DbValidator findByProperty(Dao<?> dao, String property) {
		return new UniqueInDbValidator(dao, property);
	}
}
