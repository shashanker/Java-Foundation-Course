package com.epam.course.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.course.hibernate.model.Department;

@Repository
public class DepartmentDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	public List<Department> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		/*Query<Department> query = currentSession.createQuery("from Department d JOIN fetch d.employees ",
				Department.class);*/
		Query<Department> query = currentSession.createQuery("from Department ",
				Department.class);
		return query.getResultList();
	}

	public Department get(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Department.class, id);
	}

	public void save(Department department) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(department);
	}

	public void delete(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(currentSession.get(Department.class, id));

	}
	
	public Department findByName(String name) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Department> query = currentSession.createQuery("from Department d where d.departmentName=:dname",
				Department.class);
		query.setParameter(1, name);
		return query.list().get(0);
	}

}
