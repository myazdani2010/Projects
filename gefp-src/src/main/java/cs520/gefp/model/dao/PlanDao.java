package cs520.gefp.model.dao;

import java.util.List;

import cs520.gefp.model.Plan;

public interface PlanDao {
	Plan getPlan( Long id );
	List<Plan> getPlans();
	Plan savePlan(Plan plan);
	void refreshPlan(Plan plan);
}
