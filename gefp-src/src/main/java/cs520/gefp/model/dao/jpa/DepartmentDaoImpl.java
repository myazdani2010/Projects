package cs520.gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs520.gefp.model.Department;
import cs520.gefp.model.Plan;
import cs520.gefp.model.dao.DepartmentDao;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Department getDepartment(Long id) {
		return entityManager.find(Department.class, id);
	}

	@Override
	public List<Department> getDepartments() {
		return entityManager.createQuery("from Department order by id", Department.class).getResultList();
	}

	@Transactional
	@Override
	public Department saveDepartment(Department department) {
		return entityManager.merge( department );
	}

	@Transactional
	@Override
	public Department changePlan(Department department, Plan plan) {
		department.setCurrentPlan(plan);
		return department;
	}
	
	@Transactional
	@Override
	public void refreshDepartment( Department department) {
		entityManager.refresh(department);
	}

}
