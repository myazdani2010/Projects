package cs520.gefp.model.dao;

import java.util.List;

import cs520.gefp.model.Department;
import cs520.gefp.model.Plan;

public interface DepartmentDao {
	Department getDepartment( Long id);
	List<Department> getDepartments();
	Department saveDepartment(Department department);
	Department changePlan(Department department, Plan plan);
	void refreshDepartment(Department department);
}
