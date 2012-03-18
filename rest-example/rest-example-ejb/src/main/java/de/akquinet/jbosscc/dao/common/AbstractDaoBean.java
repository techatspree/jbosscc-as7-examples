package de.akquinet.jbosscc.dao.common;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDaoBean<E> implements
		Dao<E> {

	protected final Class<E> entityClass;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	protected AbstractDaoBean() {
		super();
		entityClass = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected E getSingleResult(final CriteriaQuery<E> query) {
		return this.<E> getTypedSingleResult(query);
	}

	protected <T> T getTypedSingleResult(final CriteriaQuery<T> query) {
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	protected List<E> getResultList(final CriteriaQuery<E> query) {
		return entityManager.createQuery(query).getResultList();
	}

	protected List<E> getResultList(final CriteriaQuery<E> query,
			int maxresults, int firstresult) {
		return entityManager.createQuery(query).setMaxResults(maxresults)
				.setFirstResult(firstresult).getResultList();
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

	@Override
	public void persist(final E instance) {
		entityManager.persist(instance);
	}

	@Override
	public E find(final long id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public void remove(final E instance) {
		entityManager.remove(instance);
	}

	@Override
	public E merge(final E instance) {
		E merge = entityManager.merge(instance);
		entityManager.flush();
		return merge;
	}

	@Override
	public List<E> findAll() {
		CriteriaBuilder builder = getCriteriaBuilder();
		CriteriaQuery<E> query = builder.createQuery(entityClass);
		query.from(entityClass);

		return getResultList(query);
	}
}
