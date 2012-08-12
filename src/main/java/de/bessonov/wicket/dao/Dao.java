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
package de.bessonov.wicket.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

public interface Dao<T extends DomainObject> {

	@Transactional(readOnly=true)
	T load(long id);

	@Transactional(readOnly=true)
	List<T> findAll();

	@Transactional(readOnly=true)
	T getCriteriaUnique(Criterion logicalExpression);

	@Transactional
	T save(T o);

	@Transactional
	void delete(T o);

}
