package cs520.gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs520.gefp.model.Checkpoint;
import cs520.gefp.model.dao.CheckpointDao;

@Repository
public class CheckpointDaoImpl implements CheckpointDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Checkpoint getCheckpoint( Long id ) {
		return entityManager.find( Checkpoint.class, id );
	}

	@Override
	public List<Checkpoint> getCheckpoints() {
		return entityManager.createQuery( "from Checkpoint order by id" , Checkpoint.class ).getResultList();
	}

	@Transactional
	@Override
	public Checkpoint saveCheckpoint(Checkpoint checkpoint) {
		return entityManager.merge(checkpoint);
	}

}
