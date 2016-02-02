package cs520.gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs520.gefp.model.Plan;
import cs520.gefp.model.dao.PlanDao;

@Repository
public class PlanDaoImpl implements PlanDao {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED) 
	EntityManager entityManager; 
	
	@Override
	public Plan getPlan( Long id ) {
		return entityManager.find( Plan.class, id );
	}

	@Override
	public List<Plan> getPlans() {
		return entityManager.createQuery( "from Plan order by id" , Plan.class ).getResultList();
	}

	@Transactional
	@Override
	public Plan savePlan(Plan plan) {
		return entityManager.merge(plan);
	}
	
	@Transactional
	@Override
	public void refreshPlan(Plan plan) {
		entityManager.refresh(plan);
	}

}
