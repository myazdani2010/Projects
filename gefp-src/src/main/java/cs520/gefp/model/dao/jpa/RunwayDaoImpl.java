package cs520.gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs520.gefp.model.Plan;
import cs520.gefp.model.Runway;
import cs520.gefp.model.dao.RunwayDao;

@Repository
public class RunwayDaoImpl implements RunwayDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Runway getRunway(Long id) {
		return entityManager.find( Runway.class, id );
	}

	@Override
	public List<Runway> getRunways() {
		return entityManager.createQuery("from Runway order by id", Runway.class).getResultList();
	}

	@Override
	@Transactional
	public Runway saveRunway(Runway runway) {
		return entityManager.merge(runway);
	}

	@Override
	public List<Runway> getRunways(Plan plan) {
		return entityManager.createQuery("from Runway where plan = :p order by id", 
				Runway.class).setParameter("p", plan).getResultList();
	}

}
