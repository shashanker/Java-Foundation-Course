package com.epam.course.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.course.hibernate.model.Employee;
import com.epam.course.hibernate.model.Employee_;

@Repository
public class EmployeeDAO {

	@Autowired
	private EntityManager entityManager;

	public List<Employee> get() {
		 Session currentSession = entityManager.unwrap(Session.class);
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cBuilder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		return entityManager.createQuery(query).getResultList();
		
		 /*Query<Employee> query = currentSession.createQuery("from Employee",
		 Employee.class); return query.getResultList();*/
		 
	}

	public Employee get(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Employee.class, id);
	}

	public void save(Employee employee) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(employee);
	}

	public void delete(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(currentSession.get(Employee.class, id));

	}

	public List<Employee> findByGender(String gender) {
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cBuilder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		query.where(cBuilder.equal(root.get(Employee_.GENDER), gender));
		return entityManager.createQuery(query).getResultList();

	}
}
