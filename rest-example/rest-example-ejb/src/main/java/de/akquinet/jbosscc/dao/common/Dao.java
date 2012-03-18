package de.akquinet.jbosscc.dao.common;

import java.util.List;

public interface Dao<E> {

	void persist(E instance);

	E find(long id);

	void remove(E instance);

	E merge(E instance);

	List<E> findAll();

}
