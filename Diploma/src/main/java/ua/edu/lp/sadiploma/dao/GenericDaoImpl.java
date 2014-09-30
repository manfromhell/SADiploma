package ua.edu.lp.sadiploma.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	@PersistenceContext
	protected EntityManager entityManager;

	public void create(T entity){
		entityManager.persist(entity);
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entityManager.merge(entity));
		entityManager.flush();
	}

	public T findById(Class<T> entityClass, Long id) {
		return entityManager.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClass) {
		List<T> result = null;
		TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery("from "
				+ entityClass.getName());
		result = query.getResultList();
		return result;

	}

}
