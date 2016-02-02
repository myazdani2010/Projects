package cs520.gefp.model.dao;

import java.util.List;

import cs520.gefp.model.Plan;
import cs520.gefp.model.Runway;

public interface RunwayDao {
	Runway getRunway( Long id );
	List<Runway> getRunways();
	Runway saveRunway(Runway runway);
	List<Runway> getRunways(Plan plan);
	
}
