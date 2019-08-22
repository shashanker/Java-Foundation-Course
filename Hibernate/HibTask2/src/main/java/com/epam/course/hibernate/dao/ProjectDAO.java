package com.epam.course.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.course.hibernate.model.Project;

@Repository
public class ProjectDAO {

	@Autowired
	private EntityManager entityManager;

	public List<Project> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Project> query = cBuilder.createQuery(Project.class);
		Root<Project> root = query.from(Project.class);
		return entityManager.createQuery(query).getResultList();

	}

	public Project get(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Project.class, id);
	}

	public void save(Project project) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(project);
	}

	public void delete(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(currentSession.get(Project.class, id));

	}
}
