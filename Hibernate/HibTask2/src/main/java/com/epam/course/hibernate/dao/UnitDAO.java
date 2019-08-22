package com.epam.course.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.course.hibernate.model.Unit;

@Repository
public class UnitDAO {

	@Autowired
	private EntityManager entityManager;

	public List<Unit> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Unit> query = cBuilder.createQuery(Unit.class);
		Root<Unit> root = query.from(Unit.class);
		return entityManager.createQuery(query).getResultList();

	}

	public Unit get(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Unit.class, id);
	}

	public void save(Unit unit) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(unit);
	}

	public void delete(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(currentSession.get(Unit.class, id));

	}
}
