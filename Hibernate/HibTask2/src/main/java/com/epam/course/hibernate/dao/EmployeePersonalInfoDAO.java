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
import com.epam.course.hibernate.model.EmployeePersonalInfo;
import com.epam.course.hibernate.model.EmployeePersonalInfo_;

@Repository
public class EmployeePersonalInfoDAO {

	@Autowired
	private EntityManager entityManager;

	public List<Employee> get() {
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cBuilder.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		return entityManager.createQuery(query).getResultList();
		
		 /*Query<Employee> query = currentSession.createQuery("from Employee",
		 Employee.class); return query.getResultList();*/
		 
	}

	public EmployeePersonalInfo get(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(EmployeePersonalInfo.class, id);
	}

	public void save(EmployeePersonalInfo info) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(info);
	}

	public void delete(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(currentSession.get(EmployeePersonalInfo.class, id));

	}
	
	public EmployeePersonalInfo findByEmpId(Long id) {
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmployeePersonalInfo> query = cBuilder.createQuery(EmployeePersonalInfo.class);
		Root<EmployeePersonalInfo> root = query.from(EmployeePersonalInfo.class);
		query.where(cBuilder.equal(root.get(EmployeePersonalInfo_.EMPLOYEE), id));
		return entityManager.createQuery(query).getSingleResult();
	}

}
