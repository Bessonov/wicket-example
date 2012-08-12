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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

public abstract class AbstractHibernateDaoImpl<T extends DomainObject>
		implements Dao<T> {
	
	private Class<T> domainClass;
	
	private SessionFactory sessionFactory;
	
	public AbstractHibernateDaoImpl(Class<T> domainClass) {
		this.domainClass =  domainClass;
	}

	protected Class<T> getDomainClass() {
		return domainClass;
	}
	
	// injected from Spring
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void delete(T object) {
		getSession().delete(object);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T load(long id) {
		return (T) getSession().get(domainClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T object) {
		return (T) getSession().merge(object);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getSession().createCriteria(domainClass).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getCriteriaUnique(Criterion exp) {
		return (T) getSession()
			.createCriteria(domainClass)
			.add(exp)
			.uniqueResult();
	}
}
