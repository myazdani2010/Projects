package cs520.gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs520.gefp.model.Stage;
import cs520.gefp.model.dao.StageDao;

@Repository
public class StageDaoImpl implements StageDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Stage getStage(Long id) {
		return entityManager.find(Stage.class, id);
	}

	@Override
	public List<Stage> getStages() {
		return entityManager.createQuery("from Stage order by id", Stage.class).getResultList();
	}

	@Transactional
	@Override
	public Stage saveStage(Stage stage) {
		return entityManager.merge(stage);
	}

	
}
