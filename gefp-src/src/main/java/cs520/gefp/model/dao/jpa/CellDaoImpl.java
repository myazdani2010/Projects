package cs520.gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs520.gefp.model.Cell;
import cs520.gefp.model.Plan;
import cs520.gefp.model.Runway;
import cs520.gefp.model.Stage;
import cs520.gefp.model.dao.CellDao;

@Repository
public class CellDaoImpl implements CellDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Cell getCell( Long id ) {
		return entityManager.find( Cell.class, id );
	}

	@Override
	public List<Cell> getCells() {
		return entityManager.createQuery( "from Cell order by id asc", Cell.class ).getResultList();
	}

	@Transactional
	@Override
	public Cell saveCell(Cell cell) {
		return entityManager.merge(cell);
	}

	
	@Override
	public Cell getCell(Plan plan, Runway runway, Stage stage) {
		
		List<Cell> cells = entityManager.createQuery("from Cell where runway = :r and stage = :s and plan = :p", Cell.class)
				.setParameter("r", runway)
				.setParameter("s", stage)
				.setParameter("p", plan)
				.getResultList();
		
		if(cells.size() > 0)
			return cells.get(0);
		else
			return null;
	}

	
	
}
