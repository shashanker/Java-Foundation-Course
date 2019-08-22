package com.epam.course.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.course.hibernate.dao.UnitDAO;
import com.epam.course.hibernate.model.Unit;

@Service
public class UnitService {

	@Autowired
	private UnitDAO unitDAO;
	
	@Transactional
	public List<Unit> get() {
		return unitDAO.get();
	}

	@Transactional
	public Unit get(Long id) {
		return unitDAO.get(id);
	}

	@Transactional
	public void save(Unit unit) {
		unitDAO.save(unit);
	}

	@Transactional
	public void delete(Long id) {
		unitDAO.delete(id);
	}
	
}
